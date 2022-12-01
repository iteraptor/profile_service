package com.devlife.profile_service.service;

import com.devlife.profile_service.dto.CountryDto;
import com.devlife.profile_service.entity.Country;
import com.devlife.profile_service.mapper.CountryMapper;
import com.devlife.profile_service.repository.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CountryService {

    private final CountryRepository countryRepository;
    private final CountryMapper mapper;

    public Long addCountry(CountryDto country) {
        Country saveCountry = countryRepository.save(mapper.convertToEntity(country));
        if (saveCountry != null) {
            return saveCountry.getId();
        }
        return null;
    }

    public CountryDto getCountry(Long id) {
        Country country = countryRepository.getById(id);
        return mapper.convertToDto(country);
    }

    public List<CountryDto> getAllCountries() {
        List<Country> countryList = countryRepository.findAll();
        return countryList.stream().map(mapper::convertToDto).collect(Collectors.toList());
    }

    public Boolean deleteCountryById(Long id) {
        countryRepository.deleteById(id);
        return !countryRepository.existsById(id);
    }

}
