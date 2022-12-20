package com.devlife.profile_service.service;

import com.devlife.profile_service.dto.ProfileDto;
import com.devlife.profile_service.entity.Profile;
import com.devlife.profile_service.mapper.ProfileMapper;
import com.devlife.profile_service.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileRepository profileRepository;
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

}
