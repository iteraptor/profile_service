package com.devlife.profile_service.mapper;

import com.devlife.profile_service.dto.GenderDto;
import com.devlife.profile_service.entity.Gender;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GenderMapper {

    private final ModelMapper mapper;
    public Gender convertToEntity(GenderDto genderDto) {
        return mapper.map(genderDto, Gender.class);
    }
    public GenderDto convertToDto(Gender genderEntity) {
        return mapper.map(genderEntity, GenderDto.class);
    }

}
