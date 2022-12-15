package com.devlife.profile_service.controller.v1;

import com.devlife.profile_service.dto.EmployerDto;
import com.devlife.profile_service.service.EmployerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/employer")
public class EmployerController {

    private final EmployerService service;

    @PutMapping
    Long addEmployer(@RequestBody EmployerDto employerDto) {
        return service.addEmployer(employerDto);
    }

    @GetMapping("/{id}")
    EmployerDto getEmployer(@PathVariable("id") Long id) {
        return (service.getEmployer(id));
    }

    @GetMapping
    List<EmployerDto> getAllEmployers() {
        return service.getAllEmployers();
    }

    @DeleteMapping("/{id}")
    Boolean deleteEmployerById(@PathVariable("id") Long id) {
        return service.deleteEmployerById(id);
    }

}
