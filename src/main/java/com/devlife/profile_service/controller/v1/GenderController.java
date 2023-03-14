package com.devlife.profile_service.controller.v1;

import com.devlife.profile_service.dto.GenderDto;
import com.devlife.profile_service.service.GenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/gender")
public class GenderController {

    private final GenderService service;

    @PreAuthorize("hasAuthority('ROOT')")
    @PutMapping
    Long addGender(@RequestBody GenderDto genderDto) {
        return service.addGender(genderDto);
    }

    @PreAuthorize("hasAuthority('ROOT')")
    @GetMapping("/{id}")
    GenderDto getGender(@PathVariable("id") Long id) {
        return (service.getGender(id));
    }

    @PreAuthorize("hasAuthority('ROOT')")
    @GetMapping
    List<GenderDto> getAllGenders() {
        return service.getAllGenders();
    }

    @PreAuthorize("hasAuthority('ROOT')")
    @DeleteMapping("/{id}")
    Boolean deleteGenderById(@PathVariable("id") Long id) {
        return service.deleteGenderById(id);
    }

}
