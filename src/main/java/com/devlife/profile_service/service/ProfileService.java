package com.devlife.profile_service.service;

import com.devlife.profile_service.dto.ProfileDto;
import com.devlife.profile_service.dto.UserDto;
import com.devlife.profile_service.dto.apiRequestDto.InitProfileReq;
import com.devlife.profile_service.dto.apiRequestDto.UpdateProfileByProfileIdReq;
import com.devlife.profile_service.entity.Profile;
import com.devlife.profile_service.enums.EventType;
import com.devlife.profile_service.eventPublisher.UserPublisher;
import com.devlife.profile_service.exception.*;
import com.devlife.profile_service.mapper.ProfileMapper;
import com.devlife.profile_service.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileMapper profileMapper;
    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;
    private final CountryRepository countryRepository;
    private final GenderRepository genderRepository;
    private final AvatarRepository avatarRepository;
    private final UserPublisher userPublisher;
    private final UserOperationsService userOperationsService;

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
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException(Objects.toString(userId));
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

        profileUpdate.setUser(profile.getUser());
        profileUpdate.setNickname(profile.getNickname());

        boolean necessarySendMessage = false;

        if (profileUpdate.getFirstName() == null) {
            profileUpdate.setFirstName(profile.getFirstName());
            necessarySendMessage = true;
        }
        if (profileUpdate.getMiddleName() == null) profileUpdate.setMiddleName(profile.getMiddleName());
        if (profileUpdate.getLastName() == null) {
            profileUpdate.setLastName(profile.getLastName());
            necessarySendMessage = true;
        }
        if (profileUpdate.getPersonalInformation() == null)
            profileUpdate.setPersonalInformation(profile.getPersonalInformation());
        if (profileUpdate.getDateOfBirth() == null) profileUpdate.setDateOfBirth(profile.getDateOfBirth());
        if (profileUpdate.getCountry() == null) profileUpdate.setCountry(profile.getCountry());
        if (profileUpdate.getCity() == null) profileUpdate.setCity(profile.getCity());
        if (profileUpdate.getAvatar() == null) profileUpdate.setAvatar(profile.getAvatar());
        if (profileUpdate.getGender() == null) profileUpdate.setGender(profile.getGender());

        ProfileDto profileDto = profileMapper.convertToDto(profileRepository.saveAndFlush(profileUpdate));

        if (necessarySendMessage) {
            userPublisher.sendMessage(profileDto.getUser(), EventType.UPDATE);
        }
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

    public UserDto profileInit(InitProfileReq initProfileReq) {
        UserDto userDto = userOperationsService.initUserWithProfileAndContactInfo(initProfileReq);
        userPublisher.sendMessage(userDto, EventType.CREATE);
        return userDto;
    }

}
