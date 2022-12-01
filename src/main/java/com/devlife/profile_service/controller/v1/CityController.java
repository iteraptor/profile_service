package com.devlife.profile_service.controller.v1;

import com.devlife.profile_service.dto.CityDto;
import com.devlife.profile_service.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/")
public class CityController {

    private final CityService service;

    @PutMapping("city")
    Long addCity(@RequestBody CityDto cityDto) {
        return service.addCity(cityDto);
    }

    @GetMapping("city/{id}")
    CityDto getCity(@PathVariable("id") Long id) {
        return (service.getCity(id));
    }

    @GetMapping("city")
    List<CityDto> getAllCities() {
        return service.getAllCities();
    }

    @DeleteMapping("city/{id}")
    Boolean deleteCityById(@PathVariable("id") Long id) {
        return service.deleteCityById(id);
    }

}
