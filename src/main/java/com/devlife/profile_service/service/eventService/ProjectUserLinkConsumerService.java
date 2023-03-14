package com.devlife.profile_service.service.eventService;

import com.devlife.profile_service.dto.asyncMessageModel.ProjectUserLinkAsyncModel;
import com.devlife.profile_service.entity.Profile;
import com.devlife.profile_service.entity.Project;
import com.devlife.profile_service.entity.User;
import com.devlife.profile_service.enums.EventType;
import com.devlife.profile_service.exception.ProjectNotFoundException;
import com.devlife.profile_service.exception.ProjectUserLinkNotFound;
import com.devlife.profile_service.exception.UserNotFoundException;
import com.devlife.profile_service.repository.ProjectRepository;
import com.devlife.profile_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Set;

@Service
@Log4j2
@RequiredArgsConstructor
public class ProjectUserLinkConsumerService implements ConsumerService<ProjectUserLinkAsyncModel> {
    public static final String EVENT = "event";
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    @Override
    public void handle(Message<ProjectUserLinkAsyncModel> message) {
        validate(message);

        MessageHeaders headers = message.getHeaders();
        ProjectUserLinkAsyncModel payload = message.getPayload();
        EventType event = EventType.getByName(headers.get(EVENT, String.class));

        final Long projectExternalId = payload.getProject().getId();
        final Project project = projectRepository.findByExternalId(projectExternalId)
                .orElseThrow(() -> new ProjectNotFoundException("externalId", projectExternalId));

        final User user = userRepository.findById(payload.getUser().getExternalId())
                .orElseThrow(() -> new UserNotFoundException("id", projectExternalId));

        Set<Profile> profiles = project.getProfiles();
        if (event == EventType.CREATE) {
            profiles.add(user.getProfile());
        }

        if (event == EventType.DELETE) {
            Long id = user.getProfile().getId();
            Profile foundedProfile = profiles.stream().filter(p -> p.getId().equals(id)).findFirst()
                    .orElseThrow(() -> new ProjectUserLinkNotFound("profileId", id));
            profiles.remove(foundedProfile);
        }
        projectRepository.save(project);
    }

    private void validate(Message<ProjectUserLinkAsyncModel> message) {
        Objects.requireNonNull(message.getHeaders().get(EVENT, String.class), "header \"event\" must be");

        ProjectUserLinkAsyncModel payload = message.getPayload();
        Objects.requireNonNull(payload.getUser(), "User is require");
        Objects.requireNonNull(payload.getProject(), "Project is require");
        Objects.requireNonNull(payload.getUser().getExternalId(), "user external id is require");
        Objects.requireNonNull(payload.getProject().getId(), "project id is require");
    }
}
