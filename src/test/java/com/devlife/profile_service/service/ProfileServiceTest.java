package com.devlife.profile_service.service;

import com.devlife.profile_service.enums.ContactType;
import com.devlife.profile_service.config.MainConfig;
import com.devlife.profile_service.dto.ProfileDto;
import com.devlife.profile_service.dto.apiRequestDto.InitProfileReq;
import com.devlife.profile_service.entity.Authorization;
import com.devlife.profile_service.entity.ContactInformation;
import com.devlife.profile_service.entity.Profile;
import com.devlife.profile_service.exception.ExternalUserIdAlreadyExistsException;
import com.devlife.profile_service.exception.NicknameAlreadyExistsException;
import com.devlife.profile_service.mapper.ProfileMapper;
import com.devlife.profile_service.repository.AuthorizationRepository;
import com.devlife.profile_service.repository.ContactInformationRepository;
import com.devlife.profile_service.repository.ProfileRepository;
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

    @Mock
    ProfileRepository profileRepository;
    @Mock
    AuthorizationRepository authorizationRepository;
    @Mock
    ContactInformationRepository contactInformationRepository;

    @InjectMocks
    ProfileService profileService;

    @AfterEach
    void afterEach(){
        verifyNoMoreInteractions(profileRepository);
        verifyNoMoreInteractions(authorizationRepository);
        verifyNoMoreInteractions(contactInformationRepository);
    }

    @Test
    @DisplayName("Test of profile init method")
    void profileInit_OK() {
        InitProfileReq initProfileReq = InitProfileReq.builder()
                .externalId(1L)
                .contactInfoList(Collections.singletonList(
                        new InitProfileReq.ContactInfo(ContactType.EMAIL.getValue(), "123@test.com"))
                )
                .nickname("test")
                .build();

        Profile profileForSave = mapper.convertFromInitProfileReqToEntity(initProfileReq);
        
        Set<ContactInformation> contactInformationForSave = initProfileReq.getContactInfoList().stream()
                .map(i -> ContactInformation.builder()
                .profile(Profile.builder().id(1L).build())
                .contactType(ContactType.getByValue(i.getContactType()))
                .primaryInfo(true)
                .forAuth(true)
                .value(i.getContactValue())
                .build())
                .collect(Collectors.toSet());

        when(profileRepository.existsByNickname(any())).thenReturn(false);
        when(authorizationRepository.existsByAuthUserId(any())).thenReturn(false);

        when(profileRepository.saveAndFlush(profileForSave)).thenReturn(Profile.builder().id(1L).build());

        ProfileDto profileDto = profileService.profileInit(initProfileReq);
        assertNotNull(profileDto);
        assertEquals( 1, profileDto.getId());

        verify(authorizationRepository, times(1)).existsByAuthUserId(1L);
        verify(profileRepository, times(1)).existsByNickname("test");

        verify(profileRepository, times(1)).saveAndFlush(profileForSave);
        verify(authorizationRepository, times(1)).save(any(Authorization.class));
        verify(contactInformationRepository, times(1)).saveAll(contactInformationForSave);
    }

    @Test
    @DisplayName("Test of profile init method with exception")
    void profileInit_EXCEPTION() {
        InitProfileReq initProfileReq = InitProfileReq.builder()
                .externalId(1L)
                .nickname("test")
                .build();

        when(profileRepository.existsByNickname(any())).thenReturn(true);
        when(authorizationRepository.existsByAuthUserId(any())).thenReturn(false);

        assertThrows(NicknameAlreadyExistsException.class, () -> profileService.profileInit(initProfileReq));

        when(profileRepository.existsByNickname(any())).thenReturn(false);
        when(authorizationRepository.existsByAuthUserId(any())).thenReturn(true);

        assertThrows(ExternalUserIdAlreadyExistsException.class, () -> profileService.profileInit(initProfileReq));
    }
}