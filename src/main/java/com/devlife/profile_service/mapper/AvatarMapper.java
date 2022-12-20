package com.devlife.profile_service.mapper;

import com.devlife.profile_service.dto.AvatarDto;
import com.devlife.profile_service.entity.Avatar;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AvatarMapper {

    private final ModelMapper mapper;
    public Avatar convertToEntity(AvatarDto avatarDto) {
        return mapper.map(avatarDto, Avatar.class);
    }
    public AvatarDto convertToDto(Avatar avatarEntity) {
        return mapper.map(avatarEntity, AvatarDto.class);
    }

}
