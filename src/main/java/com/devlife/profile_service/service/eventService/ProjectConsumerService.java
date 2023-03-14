package com.devlife.profile_service.service.eventService;

import com.devlife.profile_service.dto.asyncMessageModel.ProjectAsyncModel;
import com.devlife.profile_service.dto.asyncMessageModel.UserAsyncModel;
import com.devlife.profile_service.entity.Profile;
import com.devlife.profile_service.entity.Project;
import com.devlife.profile_service.enums.EventType;
import com.devlife.profile_service.exception.BusinessLogicException;
import com.devlife.profile_service.exception.ProjectAlreadyExistsException;
import com.devlife.profile_service.exception.ProjectNotFoundException;
import com.devlife.profile_service.mapper.ProjectMapper;
import com.devlife.profile_service.repository.ProfileRepository;
import com.devlife.profile_service.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class ProjectConsumerService implements ConsumerService<ProjectAsyncModel> {
    public static final String EVENT = "event";
    private final ProjectRepository projectRepository;
    private final ProfileRepository profileRepository;
    private final ProjectMapper projectMapper;

    @Override
    @Transactional
    public void handle(Message<ProjectAsyncModel> message) {
        validate(message);

        MessageHeaders headers = message.getHeaders();
        ProjectAsyncModel payload = message.getPayload();
        Project project = projectMapper.convertToEntity(payload);
        EventType event = EventType.getByName(headers.get(EVENT, String.class));

        final Long externalId = project.getExternalId();
        final Boolean projectExists = projectRepository.existsByExternalId(externalId);
        if (event == EventType.DELETE) {
            if (!projectExists) {
                throw new ProjectNotFoundException(Objects.toString(externalId));
            }
            projectRepository.deleteByExternalId(externalId);
        }
        if (Objects.nonNull(payload.getUserGroup().getUsers())) {
            Set<Long> externalUserIdSet = payload.getUserGroup().getUsers().stream()
                    .map(UserAsyncModel::getExternalId).collect(Collectors.toSet());
            Set<Profile> profiles = profileRepository.getProfilesByUserIdIn(externalUserIdSet);
            project.setProfiles(profiles);
        }
        if (event == EventType.CREATE) {
            if (projectExists) {
                throw new ProjectAlreadyExistsException(project.getExternalId());
            }
            projectRepository.save(project);
            return;
        }

        if (event == EventType.UPDATE) {
            if (!projectExists) {
                throw new ProjectNotFoundException(Objects.toString(externalId));
            }
            Project existsProject = projectRepository.findByExternalId(externalId).map(p -> {
                if (!p.getName().equals(project.getName())) {
                    p.setName(project.getName());
                }
                if (!p.getStartDate().equals(project.getStartDate())) {
                    p.setStartDate(project.getStartDate());
                }
                if (!p.getEndDate().equals(project.getEndDate())) {
                    p.setEndDate(project.getEndDate());
                }
                if (!p.getDescription().equals(project.getDescription())) {
                    p.setDescription(project.getDescription());
                }
                return p;
            }).orElseThrow(() -> new BusinessLogicException("unreal exception"));
            projectRepository.save(existsProject);
        }
    }

    private void validate(Message<ProjectAsyncModel> message) {
        Objects.requireNonNull(message.getHeaders().get(EVENT, String.class), "header \"event\" must be");

        ProjectAsyncModel payload = message.getPayload();
        Objects.requireNonNull(payload.getId(), "id is require");
    }
}
