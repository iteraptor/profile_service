package com.devlife.profile_service.controller.v1;

import com.devlife.profile_service.dto.EducationDto;
import com.devlife.profile_service.service.EducationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/education")
public class EducationController {

    private final EducationService service;

    @PreAuthorize("hasAuthority('ROOT')")
    @PutMapping
    Long addEducation(@RequestBody EducationDto educationDto) {
        return service.addEducation(educationDto);
    }

    @PreAuthorize("hasAuthority('ROOT')")
    @GetMapping("/{id}")
    EducationDto getEducation(@PathVariable("id") Long id) {
        return (service.getEducation(id));
    }

    @PreAuthorize("hasAuthority('ROOT')")
    @GetMapping
    List<EducationDto> getAllEducations() {
        return service.getAllEducations();
    }

    @PreAuthorize("hasAuthority('ROOT')")
    @DeleteMapping("/{id}")
    Boolean deleteEducationById(@PathVariable("id") Long id) {
        return service.deleteEducationById(id);
    }

}
