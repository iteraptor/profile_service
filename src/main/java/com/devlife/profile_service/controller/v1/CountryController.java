package com.devlife.profile_service.controller.v1;

import com.devlife.profile_service.dto.CountryDto;
import com.devlife.profile_service.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/country")
public class CountryController {

    private final CountryService service;

    @PreAuthorize("hasAuthority('ROOT')")
    @PutMapping
    Long addCountry(@RequestBody CountryDto countryDto) {
        return service.addCountry(countryDto);
    }

    @PreAuthorize("hasAuthority('ROOT')")
    @GetMapping("/{id}")
    CountryDto getCountry(@PathVariable("id") Long id) {
        return (service.getCountry(id));
    }

    @PreAuthorize("hasAuthority('ROOT')")
    @GetMapping
    List<CountryDto> getAllCountries() {
        return service.getAllCountries();
    }

    @PreAuthorize("hasAuthority('ROOT')")
    @DeleteMapping("/{id}")
    Boolean deleteCountryById(@PathVariable("id") Long id) {
        return service.deleteCountryById(id);
    }

}
