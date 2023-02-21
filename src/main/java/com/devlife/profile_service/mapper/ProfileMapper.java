package com.devlife.profile_service.mapper;

import com.devlife.profile_service.dto.ProfileDto;
import com.devlife.profile_service.dto.apiRequestDto.InitProfileReq;
import com.devlife.profile_service.dto.apiRequestDto.UpdateProfileByProfileIdReq;
import com.devlife.profile_service.entity.*;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

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

    public Profile convertUpdateProfileByProfileIdReqToEntity(UpdateProfileByProfileIdReq updateProfileByProfileIdReq, Long profileId) {
        Profile profile = mapper.map(updateProfileByProfileIdReq, Profile.class);
        profile.setId(profileId);
        return profile;
    }

    public Profile convertFromInitProfileReqToEntity(InitProfileReq initProfileReq) {
        return mapper.map(initProfileReq, Profile.class);
    }

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(UpdateProfileByProfileIdReq.class, Profile.class)
        .addMappings(m -> m.skip(Profile::setAvatar))
        .addMappings(m -> m.skip(Profile::setCity))
        .addMappings(m -> m.skip(Profile::setCountry))
        .addMappings(m -> m.skip(Profile::setGender))
        .setPostConverter(mappingContext -> {
            UpdateProfileByProfileIdReq source = mappingContext.getSource();
            Profile destination = mappingContext.getDestination();
            destination.setAvatar(source.getAvatarId() != null ? Avatar.builder().id(source.getAvatarId()).build() : null);
            destination.setCity(source.getCityId() != null ? City.builder().id(source.getCityId()).build() : null);
            destination.setCountry(source.getCountryId() != null ? Country.builder().id(source.getCountryId()).build() : null);
            destination.setGender(source.getGenderId() != null ? Gender.builder().id(source.getGenderId()).build() : null);
            return destination;
        });
    }

}
