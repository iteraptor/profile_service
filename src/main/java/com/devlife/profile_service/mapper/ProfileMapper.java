package com.devlife.profile_service.mapper;

import com.devlife.profile_service.dto.ProfileDto;
import com.devlife.profile_service.entity.Profile;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProfileMapper {

    private final ModelMapper mapper;
    public Profile convertToEntity(ProfileDto profileDto) {
        return mapper.map(profileDto, Profile.class);
    }
    public ProfileDto convertToDto(Profile profileEntity) {
        return mapper.map(profileEntity, ProfileDto.class);
    }

}
