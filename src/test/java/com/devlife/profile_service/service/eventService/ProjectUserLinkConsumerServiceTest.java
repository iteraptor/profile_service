package com.devlife.profile_service.service.eventService;

import com.devlife.profile_service.dto.asyncMessageModel.ProjectAsyncModel;
import com.devlife.profile_service.dto.asyncMessageModel.ProjectUserLinkAsyncModel;
import com.devlife.profile_service.dto.asyncMessageModel.UserAsyncModel;
import com.devlife.profile_service.entity.Profile;
import com.devlife.profile_service.entity.Project;
import com.devlife.profile_service.entity.User;
import com.devlife.profile_service.enums.EventType;
import com.devlife.profile_service.exception.ProjectNotFoundException;
import com.devlife.profile_service.repository.ProjectRepository;
import com.devlife.profile_service.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;

import java.util.HashSet;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DisplayName("tests of input link user - project")
@ExtendWith(MockitoExtension.class)
class ProjectUserLinkConsumerServiceTest {

    @Mock
    ProjectRepository projectRepository;
    @Mock
    UserRepository userRepository;
    @InjectMocks
    ProjectUserLinkConsumerService service;

    @AfterEach
    void afterEach() {
        verifyNoMoreInteractions(projectRepository);
        verifyNoMoreInteractions(userRepository);
    }

    @Test
    public void testHandleSuccess() {
        ProjectUserLinkAsyncModel payload = new ProjectUserLinkAsyncModel();
        payload.setProject(new ProjectAsyncModel());
        payload.getProject().setId(1L);
        payload.setUser(new UserAsyncModel());
        payload.getUser().setExternalId(2L);
        Message<ProjectUserLinkAsyncModel> message = MessageBuilder.withPayload(payload)
                .setHeader(ProjectUserLinkConsumerService.EVENT, EventType.CREATE.toString())
                .build();
        Project project = new Project();
        project.setProfiles(new HashSet<>());
        User user = new User();
        user.setProfile(new Profile());

        when(projectRepository.findByExternalId(1L)).thenReturn(Optional.of(project));
        when(userRepository.findById(2L)).thenReturn(Optional.of(user));

        service.handle(message);

        verify(projectRepository, times(1)).save(project);
    }

    @Test
    public void testHandleMissingHeader() {
        ProjectUserLinkAsyncModel payload = new ProjectUserLinkAsyncModel();
        payload.setProject(new ProjectAsyncModel());
        payload.getProject().setId(1L);
        payload.setUser(new UserAsyncModel());
        payload.getUser().setExternalId(2L);
        Message<ProjectUserLinkAsyncModel> message = MessageBuilder.withPayload(payload).build();

        assertThrows(NullPointerException.class, () -> service.handle(message));
    }

    @Test
    public void testHandleProjectNotFound() {
        ProjectUserLinkAsyncModel payload = new ProjectUserLinkAsyncModel();
        payload.setProject(new ProjectAsyncModel());
        payload.getProject().setId(1L);
        payload.setUser(new UserAsyncModel());
        payload.getUser().setExternalId(2L);
        Message<ProjectUserLinkAsyncModel> message = MessageBuilder.withPayload(payload)
                .setHeader(ProjectUserLinkConsumerService.EVENT, EventType.CREATE.toString())
                .build();

        when(projectRepository.findByExternalId(1L)).thenReturn(Optional.empty());

        assertThrows(ProjectNotFoundException.class, () -> service.handle(message));
    }
}