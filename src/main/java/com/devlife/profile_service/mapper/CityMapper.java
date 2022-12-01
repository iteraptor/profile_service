package com.devlife.profile_service.mapper;

import com.devlife.profile_service.dto.CityDto;
import com.devlife.profile_service.entity.City;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CityMapper {

    private final ModelMapper mapper;
    public City convertToEntity(CityDto cityDto) {
        return mapper.map(cityDto, City.class);
    }
    public CityDto convertToDto(City cityEntity) {
        return mapper.map(cityEntity, CityDto.class);
    }

}
