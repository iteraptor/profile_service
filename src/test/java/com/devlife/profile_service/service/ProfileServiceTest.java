package com.devlife.profile_service.service;

import com.devlife.profile_service.config.MainConfig;
import com.devlife.profile_service.dto.UserDto;
import com.devlife.profile_service.dto.apiRequestDto.InitProfileReq;
import com.devlife.profile_service.entity.ContactInformation;
import com.devlife.profile_service.entity.Profile;
import com.devlife.profile_service.entity.User;
import com.devlife.profile_service.enums.ContactType;
import com.devlife.profile_service.enums.EventType;
import com.devlife.profile_service.eventPublisher.UserPublisher;
import com.devlife.profile_service.exception.ExternalUserIdAlreadyExistsException;
import com.devlife.profile_service.exception.NicknameAlreadyExistsException;
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

import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
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
    @InjectMocks
    ProfileService profileService;

    @AfterEach
    void afterEach(){
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

        Set<ContactInformation> contactInformationForSave = initProfileReq.getContactInfoList().stream()
                .map(i -> ContactInformation.builder()
                        .contactType(ContactType.getByValue(i.getContactType()))
                        .primaryInfo(true)
                        .forAuth(true)
                        .value(i.getContactValue())
                        .build())
                .collect(Collectors.toSet());

        Profile profileForSave = mapper.convertFromInitProfileReqToEntity(initProfileReq);

        profileForSave.setContactInformation(contactInformationForSave);

        User savedUser = User.builder()
                .profile(profileForSave)
                .dateOfRegistration(OffsetDateTime.MIN)
                .authUserId(initProfileReq.getExternalId())
                .build();

        when(profileRepository.existsByNickname(any())).thenReturn(false);
        when(userRepository.existsByAuthUserId(any())).thenReturn(false);
        when(userRepository.saveAndFlush(any(User.class))).thenReturn(savedUser);

        UserDto userDto = profileService.profileInit(initProfileReq);
        assertNotNull(userDto);
        assertEquals(initProfileReq.getExternalId(), userDto.getAuthUserId());

        assertNotNull(userDto.getProfile());
        assertEquals(initProfileReq.getNickname(), savedUser.getProfile().getNickname());
        assertEquals(initProfileReq.getLastName(), savedUser.getProfile().getLastName());
        assertEquals(initProfileReq.getFirstName(), savedUser.getProfile().getFirstName());
        assertEquals(initProfileReq.getMiddleName(), savedUser.getProfile().getMiddleName());

        assertNotNull(userDto.getProfile().getContactInformation());
        assertEquals(1, userDto.getProfile().getContactInformation().size());

        verify(userRepository, times(1)).existsByAuthUserId(1L);
        verify(profileRepository, times(1)).existsByNickname("test_nickname");
        verify(userRepository, times(1)).saveAndFlush(any(User.class));
        verify(userPublisher, times(1)).sendMessage(userMapper.convertToDto(savedUser), EventType.CREATE);
    }

    @Test
    @DisplayName("Test of profile init method with exception")
    void profileInit_EXCEPTION() {
        InitProfileReq initProfileReq = InitProfileReq.builder()
                .externalId(1L)
                .nickname("test")
                .build();

        when(profileRepository.existsByNickname(any())).thenReturn(true);
        when(userRepository.existsByAuthUserId(any())).thenReturn(false);

        assertThrows(NicknameAlreadyExistsException.class, () -> profileService.profileInit(initProfileReq));

        when(profileRepository.existsByNickname(any())).thenReturn(false);
        when(userRepository.existsByAuthUserId(any())).thenReturn(true);

        assertThrows(ExternalUserIdAlreadyExistsException.class, () -> profileService.profileInit(initProfileReq));
    }
}