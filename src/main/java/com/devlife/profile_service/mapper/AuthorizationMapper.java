package com.devlife.profile_service.mapper;

import com.devlife.profile_service.dto.AuthorizationDto;
import com.devlife.profile_service.entity.Authorization;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthorizationMapper {

    private final ModelMapper mapper;
    public Authorization convertToEntity(AuthorizationDto authorizationDto) {
        return mapper.map(authorizationDto, Authorization.class);
    }
    public AuthorizationDto convertToDto(Authorization authorizationEntity) {
        return mapper.map(authorizationEntity, AuthorizationDto.class);
    }

}
