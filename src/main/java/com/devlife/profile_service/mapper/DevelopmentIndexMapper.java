package com.devlife.profile_service.mapper;

import com.devlife.profile_service.dto.DevelopmentIndexDto;
import com.devlife.profile_service.entity.DevelopmentIndex;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DevelopmentIndexMapper {

    private final ModelMapper mapper;
    public DevelopmentIndex convertToEntity(DevelopmentIndexDto developmentIndexDto) {
        return mapper.map(developmentIndexDto, DevelopmentIndex.class);
    }
    public DevelopmentIndexDto convertToDto(DevelopmentIndex developmentIndexEntity) {
        return mapper.map(developmentIndexEntity, DevelopmentIndexDto.class);
    }

}
