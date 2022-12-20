package com.devlife.profile_service.mapper;

import com.devlife.profile_service.dto.EducationDto;
import com.devlife.profile_service.entity.Education;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EducationMapper {

    private final ModelMapper mapper;
    public Education convertToEntity(EducationDto educationDto) {
        return mapper.map(educationDto, Education.class);
    }
    public EducationDto convertToDto(Education educationEntity) {
        return mapper.map(educationEntity, EducationDto.class);
    }

}
