package com.devlife.profile_service.controller.v1;

import com.devlife.profile_service.dto.EducationDto;
import com.devlife.profile_service.service.EducationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/")
public class EducationController {

    private final EducationService service;

    @PutMapping("education")
    Long addEducation(@RequestBody EducationDto educationDto) {
        return service.addEducation(educationDto);
    }

    @GetMapping("education/{id}")
    EducationDto getEducation(@PathVariable("id") Long id) {
        return (service.getEducation(id));
    }

    @GetMapping("education")
    List<EducationDto> getAllEducations() {
        return service.getAllEducations();
    }

    @DeleteMapping("education/{id}")
    Boolean deleteEducationById(@PathVariable("id") Long id) {
        return service.deleteEducationById(id);
    }

}
