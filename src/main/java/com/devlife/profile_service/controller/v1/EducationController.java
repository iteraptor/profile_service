package com.devlife.profile_service.controller.v1;

import com.devlife.profile_service.dto.EducationDto;
import com.devlife.profile_service.service.EducationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/education")
public class EducationController {

    private final EducationService service;

    @PutMapping
    Long addEducation(@RequestBody EducationDto educationDto) {
        return service.addEducation(educationDto);
    }

    @GetMapping("/{id}")
    EducationDto getEducation(@PathVariable("id") Long id) {
        return (service.getEducation(id));
    }

    @GetMapping
    List<EducationDto> getAllEducations() {
        return service.getAllEducations();
    }

    @DeleteMapping("/{id}")
    Boolean deleteEducationById(@PathVariable("id") Long id) {
        return service.deleteEducationById(id);
    }

}
