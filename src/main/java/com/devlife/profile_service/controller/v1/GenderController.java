package com.devlife.profile_service.controller.v1;

import com.devlife.profile_service.dto.GenderDto;
import com.devlife.profile_service.service.GenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/")
public class GenderController {

    private final GenderService service;

    @PutMapping("gender")
    Long addGender(@RequestBody GenderDto genderDto) {
        return service.addGender(genderDto);
    }

    @GetMapping("gender/{id}")
    GenderDto getGender(@PathVariable("id") Long id) {
        return (service.getGender(id));
    }

    @GetMapping("gender")
    List<GenderDto> getAllGenders() {
        return service.getAllGenders();
    }

    @DeleteMapping("gender/{id}")
    Boolean deleteGenderById(@PathVariable("id") Long id) {
        return service.deleteGenderById(id);
    }

}
