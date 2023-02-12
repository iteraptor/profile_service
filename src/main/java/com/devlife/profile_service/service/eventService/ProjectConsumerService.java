package com.devlife.profile_service.service.eventService;

import com.devlife.profile_service.dto.asyncMessageModel.ProjectAsyncModel;
import com.devlife.profile_service.entity.Project;
import com.devlife.profile_service.enums.EventType;
import com.devlife.profile_service.exception.ProjectAlreadyExistsException;
import com.devlife.profile_service.exception.ProjectNotFoundException;
import com.devlife.profile_service.mapper.ProjectMapper;
import com.devlife.profile_service.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ProjectConsumerService implements ConsumerService<ProjectAsyncModel>{

    public static final String EVENT = "event";
    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;

    @Override
    public void handle(Message<ProjectAsyncModel> message) {
        validate(message);

        MessageHeaders headers = message.getHeaders();
        ProjectAsyncModel payload = message.getPayload();
        Project project = projectMapper.convertToEntity(payload);
        EventType event = EventType.getByName(headers.get(EVENT, String.class));

        Long externalId = project.getExternalId();
        boolean projectExists = projectRepository.existsByExternalId(externalId);
        if (event == EventType.CREATE) {
            if (projectExists) {
                throw new ProjectAlreadyExistsException(project.getExternalId());
            }
            projectRepository.save(project);
        }
        if (event == EventType.DELETE) {
            if (!projectExists) {
                throw new ProjectNotFoundException(project.getExternalId());
            }
            projectRepository.deleteById(project.getId());
        }
        if (event == EventType.UPDATE) {
            if (!projectExists) {
                throw new ProjectNotFoundException(project.getId());
            }
            projectRepository.save(project);
        }
    }

    private void validate(Message<ProjectAsyncModel> message) {
        Objects.requireNonNull(message.getHeaders().get(EVENT, String.class), "header \"event\" must be");

        ProjectAsyncModel payload = message.getPayload();
        Objects.requireNonNull(payload.getId(), "id is require");
        Objects.requireNonNull(payload.getName(), "name is require");
        Objects.requireNonNull(payload.getStartDate(), "start date is require");
    }
}
