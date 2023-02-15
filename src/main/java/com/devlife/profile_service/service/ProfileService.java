package com.devlife.profile_service.service;

import com.devlife.profile_service.enums.ContactType;
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
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileRepository profileRepository;
    private final AuthorizationRepository authorizationRepository;
    private final ContactInformationRepository contactInformationRepository;
    private final ProfileMapper mapper;

    public Long addProfile(ProfileDto profile) {
        Profile saveProfile = profileRepository.save(mapper.convertToEntity(profile));
        if (saveProfile != null) {
            return saveProfile.getId();
        }
        return null;
    }

    public ProfileDto getProfile(Long id) {
        Profile profile = profileRepository.getById(id);
        return mapper.convertToDto(profile);
    }

    public List<ProfileDto> getAllProfiles() {
        List<Profile> profilesList = profileRepository.findAll();
        return profilesList.stream().map(mapper::convertToDto).collect(Collectors.toList());
    }

    public Boolean deleteProfileById(Long id) {
        profileRepository.deleteById(id);
        return !profileRepository.existsById(id);
    }

    @Transactional
    public ProfileDto profileInit(InitProfileReq initProfileReq) {
        checkUser(initProfileReq.getExternalId(), initProfileReq.getNickname());
        final Profile profile = mapper.convertFromInitProfileReqToEntity(initProfileReq);
        final Profile profileSaved = profileRepository.saveAndFlush(profile);

        authorizationRepository.save(Authorization.builder()
                .profile(profileSaved)
                .dateOfRegistration(OffsetDateTime.now(ZoneId.of("Z")))
                .authUserId(initProfileReq.getExternalId())
                .build());

        contactInformationRepository.saveAll(initProfileReq.getContactInfoList().stream()
                .map(i -> ContactInformation.builder()
                        .profile(profileSaved)
                        .contactType(ContactType.getByValue(i.getContactType()))
                        .primaryInfo(true)
                        .forAuth(true)
                        .value(i.getContactValue())
                        .build()).collect(Collectors.toSet()));
        return mapper.convertToDto(profileSaved);
    }

    private void checkUser(Long externalId, String nickName) {
        if (profileRepository.existsByNickname(nickName)) {
            throw new NicknameAlreadyExistsException(nickName);
        }
        if (authorizationRepository.existsByAuthUserId(externalId)) {
            throw new ExternalUserIdAlreadyExistsException(externalId);
        }
    }
}
