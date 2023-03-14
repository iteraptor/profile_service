package com.devlife.profile_service.service;

import com.devlife.profile_service.config.MainConfig;
import com.devlife.profile_service.dto.UserDto;
import com.devlife.profile_service.dto.apiRequestDto.InitProfileReq;
import com.devlife.profile_service.enums.ContactType;
import com.devlife.profile_service.enums.EventType;
import com.devlife.profile_service.eventPublisher.UserPublisher;
import com.devlife.profile_service.mapper.ProfileMapper;
import com.devlife.profile_service.mapper.UserMapper;
import com.devlife.profile_service.repository.ProfileRepository;
import com.devlife.profile_service.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Collections;

import static org.mockito.Mockito.*;

@DisplayName("Test of project service")
@ExtendWith(MockitoExtension.class)
class ProfileServiceTest {
    final ModelMapper modelMapper = new MainConfig().modelMapper();
    @Spy
    ProfileMapper mapper = new ProfileMapper(modelMapper);
    @Spy
    UserMapper userMapper = new UserMapper(modelMapper);
    @Mock
    ProfileRepository profileRepository;
    @Mock
    UserRepository userRepository;
    @Mock
    UserPublisher userPublisher;
    @Mock
    UserOperationsService userOperationsService;
    @InjectMocks
    ProfileService profileService;

    @AfterEach
    void afterEach() {
        verifyNoMoreInteractions(profileRepository);
        verifyNoMoreInteractions(userRepository);
        verifyNoMoreInteractions(userPublisher);
    }

    @Test
    @DisplayName("Test of profile init method")
    void profileInit_OK() {
        InitProfileReq initProfileReq = InitProfileReq.builder()
                .externalId(1L)
                .contactInfoList(Collections.singletonList(
                        new InitProfileReq.ContactInfo(ContactType.EMAIL.getValue(), "123@test.com"))
                )
                .firstName("test_firstName")
                .lastName("test_lastName")
                .middleName("test_middleName")
                .nickname("test_nickname")
                .build();

        UserDto user = UserDto.builder().id(1L).authUserId(1L).build();

        when(userOperationsService.initUserWithProfileAndContactInfo(initProfileReq)).thenReturn(user);

        UserDto userDto = profileService.profileInit(initProfileReq);

        verify(userPublisher, times(1)).sendMessage(userDto, EventType.CREATE);
    }
}