package com.devlife.profile_service.mapper;

import com.devlife.profile_service.dto.CountryDto;
import com.devlife.profile_service.entity.Country;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CountryMapper {

    private final ModelMapper mapper;
    public Country convertToEntity(CountryDto countryDto) {
        return mapper.map(countryDto, Country.class);
    }
    public CountryDto convertToDto(Country countryEntity) {
        return mapper.map(countryEntity, CountryDto.class);
    }

}
