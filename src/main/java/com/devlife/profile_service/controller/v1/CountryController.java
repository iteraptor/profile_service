package com.devlife.profile_service.controller.v1;

import com.devlife.profile_service.dto.CountryDto;
import com.devlife.profile_service.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/")
public class CountryController {

    private final CountryService service;

    @PutMapping("country")
    Long addCountry(@RequestBody CountryDto countryDto) {
        return service.addCountry(countryDto);
    }

    @GetMapping("country/{id}")
    CountryDto getCountry(@PathVariable("id") Long id) {
        return (service.getCountry(id));
    }

    @GetMapping("country")
    List<CountryDto> getAllCountries() {
        return service.getAllCountries();
    }

    @DeleteMapping("country/{id}")
    Boolean deleteCountryById(@PathVariable("id") Long id) {
        return service.deleteCountryById(id);
    }

}
