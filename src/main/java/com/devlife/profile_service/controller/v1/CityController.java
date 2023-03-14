package com.devlife.profile_service.controller.v1;

import com.devlife.profile_service.dto.CityDto;
import com.devlife.profile_service.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/city")
public class CityController {

    private final CityService service;

    @PreAuthorize("hasAuthority('ROOT')")
    @PutMapping
    Long addCity(@RequestBody CityDto cityDto) {
        return service.addCity(cityDto);
    }

    @PreAuthorize("hasAuthority('ROOT')")
    @GetMapping("/{id}")
    CityDto getCity(@PathVariable("id") Long id) {
        return (service.getCity(id));
    }

    @PreAuthorize("hasAuthority('ROOT')")
    @GetMapping
    List<CityDto> getAllCities() {
        return service.getAllCities();
    }

    @PreAuthorize("hasAuthority('ROOT')")
    @DeleteMapping("/{id}")
    Boolean deleteCityById(@PathVariable("id") Long id) {
        return service.deleteCityById(id);
    }

}
