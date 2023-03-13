package com.devlife.profile_service.service.eventService;

import com.devlife.profile_service.dto.asyncMessageModel.ProjectAsyncModel;
import com.devlife.profile_service.dto.asyncMessageModel.UserAsyncModel;
import com.devlife.profile_service.dto.asyncMessageModel.UserGroupAsyncModel;
import com.devlife.profile_service.entity.Project;
import com.devlife.profile_service.enums.EventType;
import com.devlife.profile_service.exception.ProjectAlreadyExistsException;
import com.devlife.profile_service.mapper.ProjectMapper;
import com.devlife.profile_service.repository.ProfileRepository;
import com.devlife.profile_service.repository.ProjectRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProjectConsumerServiceTest {
    @Mock
    private ProjectRepository projectRepository;
    @Mock
    private ProfileRepository profileRepository;
    @Mock
    private ProjectMapper projectMapper;
    @InjectMocks
    private ProjectConsumerService projectConsumerService;

    @AfterEach
    void afterEach() {
        verifyNoMoreInteractions(projectRepository);
        verifyNoMoreInteractions(profileRepository);
        verifyNoMoreInteractions(projectMapper);
    }

    @Test
    @DisplayName("Test CREATE event")
    void testCreateProject() {
        Long externalId = 12345L;

        UserAsyncModel userAsyncModel = new UserAsyncModel();
        userAsyncModel.setExternalId(1L);

        UserGroupAsyncModel userGroupAsyncModel = new UserGroupAsyncModel();
        userGroupAsyncModel.setUsers(Collections.singleton(userAsyncModel));

        ProjectAsyncModel projectAsyncModel = new ProjectAsyncModel();
        projectAsyncModel.setId(externalId);
        projectAsyncModel.setUserGroup(userGroupAsyncModel);

        Project project = new Project();
        project.setExternalId(externalId);

        when(projectMapper.convertToEntity(any(ProjectAsyncModel.class))).thenReturn(project);
        when(projectRepository.existsByExternalId(externalId)).thenReturn(false);

        Message<ProjectAsyncModel> message = MessageBuilder.withPayload(projectAsyncModel)
                .setHeader("event", EventType.CREATE.toString())
                .build();

        projectConsumerService.handle(message);

        verify(projectRepository, times(1)).save(project);
        verify(projectRepository, times(1)).existsByExternalId(externalId);
        verify(profileRepository, times(1)).getProfilesByUserIdIn(Collections.singleton(userAsyncModel.getExternalId()));
    }

    @Test
    @DisplayName("Test CREATE event with existing project")
    void testCreateProjectWithExistingProject() {
        Long externalId = 12345L;

        UserAsyncModel userAsyncModel = new UserAsyncModel();
        userAsyncModel.setExternalId(1L);

        UserGroupAsyncModel userGroupAsyncModel = new UserGroupAsyncModel();
        userGroupAsyncModel.setUsers(Collections.singleton(userAsyncModel));

        ProjectAsyncModel projectAsyncModel = new ProjectAsyncModel();
        projectAsyncModel.setId(externalId);
        projectAsyncModel.setUserGroup(userGroupAsyncModel);

        Project project = new Project();
        project.setExternalId(externalId);

        when(projectMapper.convertToEntity(any(ProjectAsyncModel.class))).thenReturn(project);
        when(projectRepository.existsByExternalId(externalId)).thenReturn(true);

        Message<ProjectAsyncModel> message = MessageBuilder.withPayload(projectAsyncModel)
                .setHeader("event", EventType.CREATE.toString())
                .build();

        assertThrows(ProjectAlreadyExistsException.class,
                () -> projectConsumerService.handle(message));

        verify(projectRepository, times(0)).save(project);
        verify(projectRepository, times(1)).existsByExternalId(externalId);
        verify(profileRepository, times(1)).getProfilesByUserIdIn(Collections.singleton(userAsyncModel.getExternalId()));
    }
}