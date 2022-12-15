package com.devlife.profile_service.controller.v1;

import com.devlife.profile_service.dto.EmployerDto;
import com.devlife.profile_service.service.EmployerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/")
public class EmployerController {

    private final EmployerService service;

    @PutMapping("employer")
    Long addEmployer(@RequestBody EmployerDto employerDto) {
        return service.addEmployer(employerDto);
    }

    @GetMapping("employer/{id}")
    EmployerDto getEmployer(@PathVariable("id") Long id) {
        return (service.getEmployer(id));
    }

    @GetMapping("employer")
    List<EmployerDto> getAllEmployers() {
        return service.getAllEmployers();
    }

    @DeleteMapping("employer/{id}")
    Boolean deleteEmployerById(@PathVariable("id") Long id) {
        return service.deleteEmployerById(id);
    }

}