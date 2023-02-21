package com.devlife.profile_service.service;

import com.devlife.profile_service.dto.ProfileDto;
import com.devlife.profile_service.dto.apiRequestDto.InitProfileReq;
import com.devlife.profile_service.dto.apiRequestDto.UpdateProfileByProfileIdReq;
import com.devlife.profile_service.entity.Authorization;
import com.devlife.profile_service.entity.ContactInformation;
import com.devlife.profile_service.entity.Profile;
import com.devlife.profile_service.enums.ContactType;
import com.devlife.profile_service.exception.*;
import com.devlife.profile_service.mapper.ProfileMapper;
import com.devlife.profile_service.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileMapper profileMapper;
    private final ProfileRepository profileRepository;
    private final AuthorizationRepository authorizationRepository;
    private final ContactInformationRepository contactInformationRepository;
    private final CountryRepository countryRepository;
    private final CityRepository cityRepository;
    private final GenderRepository genderRepository;
    private final AvatarRepository avatarRepository;

//    private final ProfilePublisher profilePublisher; TODO Для Kafka

    public ProfileDto addProfile(ProfileDto profile) {
        Profile saveProfile = profileRepository.save(profileMapper.convertToEntity(profile));
        return profileMapper.convertToDto(saveProfile);
    }

    public ProfileDto getProfile(Long id) {
        Optional<Profile> profileOpt = profileRepository.findById(id);
        Profile profile = profileOpt.orElseThrow(() -> new ProfileNotFoundException("id : " + id));
        return profileMapper.convertToDto(profile);
    }

    public ProfileDto getProfileByUserId(Long userId) {
        if (!authorizationRepository.existsById(userId)) {
            throw new UserNotFoundException("id : " + userId);
        }
        Profile profile = profileRepository.getProfileByUserId(userId);
        return profileMapper.convertToDto(profile);
    }

    @Transactional
    public ProfileDto updateProfileByProfileId(Long profileId, UpdateProfileByProfileIdReq updateProfileByProfileIdReq) {
        final Profile profile = profileRepository.findById(profileId).orElseThrow(() -> new ProfileNotFoundException("id: " + profileId));

        Long countryId = updateProfileByProfileIdReq.getCountryId();
        if (countryId != null && !countryRepository.existsById(countryId)) {
            throw new CountryNotFoundException("id : " + countryId);
        }

        Long cityId = updateProfileByProfileIdReq.getCityId();
        if (cityId != null && !countryRepository.existsById(cityId)) {
            throw new CityNotFoundException("id : " + cityId);
        }

        Long avatarId = updateProfileByProfileIdReq.getAvatarId();
        if (avatarId != null && !avatarRepository.existsById(avatarId)) {
            throw new AvatarNotFoundException("id : " + avatarId);
        }

        Long genderId = updateProfileByProfileIdReq.getGenderId();
        if (genderId != null && !genderRepository.existsById(genderId)) {
            throw new GenderNotFoundException("id : " + genderId);
        }

        Profile profileUpdate = profileMapper.convertUpdateProfileByProfileIdReqToEntity(updateProfileByProfileIdReq, profile.getId());

        if (profileUpdate.getFirstName() == null) profileUpdate.setFirstName(profile.getFirstName());
        if (profileUpdate.getMiddleName() == null) profileUpdate.setMiddleName(profile.getMiddleName());
        if (profileUpdate.getLastName() == null) profileUpdate.setLastName(profile.getLastName());
        if (profileUpdate.getPersonalInformation() == null) profileUpdate.setPersonalInformation(profile.getPersonalInformation());
        if (profileUpdate.getDateOfBirth() == null) profileUpdate.setDateOfBirth(profile.getDateOfBirth());
        if (profileUpdate.getCountry() == null) profileUpdate.setCountry(profile.getCountry());
        if (profileUpdate.getCity() == null) profileUpdate.setCity(profile.getCity());
        if (profileUpdate.getAvatar() == null) profileUpdate.setAvatar(profile.getAvatar());
        if (profileUpdate.getGender() == null) profileUpdate.setGender(profile.getGender());
        if (profileUpdate.getNickname() == null) profileUpdate.setNickname(profile.getNickname());

        ProfileDto profileDto = profileMapper.convertToDto(profileRepository.save(profileUpdate));

//        profilePublisher.sendMessage(profileDto, EventType.UPDATE); TODO для Kafka

        return profileDto;
    }

    public List<ProfileDto> getAllProfiles() {
        List<Profile> profilesList = profileRepository.findAll();
        return profilesList.stream().map(profileMapper::convertToDto).collect(Collectors.toList());
    }

    public Boolean deleteProfileById(Long id) {
        profileRepository.deleteById(id);
        return !profileRepository.existsById(id);
    }

    @Transactional
    public ProfileDto profileInit(InitProfileReq initProfileReq) {
        checkUser(initProfileReq.getExternalId(), initProfileReq.getNickname());
        final Profile profile = profileMapper.convertFromInitProfileReqToEntity(initProfileReq);
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
        return profileMapper.convertToDto(profileSaved);
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
