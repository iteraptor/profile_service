package com.devlife.profile_service.service;

import com.devlife.profile_service.dto.CityDto;
import com.devlife.profile_service.entity.City;
import com.devlife.profile_service.mapper.CityMapper;
import com.devlife.profile_service.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CityService {

    private final CityRepository cityRepository;
    private final CityMapper mapper;

    public Long addCity(CityDto city) {
        City saveCity = cityRepository.save(mapper.convertToEntity(city));
        if (saveCity != null) {
            return saveCity.getId();
        }
        return null;
    }

    public CityDto getCity(Long id) {
        City city = cityRepository.getById(id);
        return mapper.convertToDto(city);
    }

    public List<CityDto> getAllCities() {
        List<City> citiesList = cityRepository.findAll();
        return citiesList.stream().map(mapper::convertToDto).collect(Collectors.toList());
    }

    public Boolean deleteCityById(Long id) {
        cityRepository.deleteById(id);
        return !cityRepository.existsById(id);
    }

}
