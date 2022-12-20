package com.devlife.profile_service.controller.v1;

import com.devlife.profile_service.dto.GenderDto;
import com.devlife.profile_service.service.GenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/gender")
public class GenderController {

    private final GenderService service;

    @PutMapping
    Long addGender(@RequestBody GenderDto genderDto) {
        return service.addGender(genderDto);
    }

    @GetMapping("/{id}")
    GenderDto getGender(@PathVariable("id") Long id) {
        return (service.getGender(id));
    }

    @GetMapping
    List<GenderDto> getAllGenders() {
        return service.getAllGenders();
    }

    @DeleteMapping("/{id}")
    Boolean deleteGenderById(@PathVariable("id") Long id) {
        return service.deleteGenderById(id);
    }

}
