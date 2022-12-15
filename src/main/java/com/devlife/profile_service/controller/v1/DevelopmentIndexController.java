package com.devlife.profile_service.controller.v1;

import com.devlife.profile_service.dto.DevelopmentIndexDto;
import com.devlife.profile_service.service.DevelopmentIndexService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/developmentIndex")
public class DevelopmentIndexController {

    private final DevelopmentIndexService service;

    @PutMapping
    Long addDevelopmentIndex(@RequestBody DevelopmentIndexDto developmentIndexDto) {
        return service.addDevelopmentIndex(developmentIndexDto);
    }

    @GetMapping("/{id}")
    DevelopmentIndexDto getDevelopmentIndex(@PathVariable("id") Long id) {
        return (service.getDevelopmentIndex(id));
    }

    @GetMapping
    List<DevelopmentIndexDto> getAllDevelopmentIndexes() {
        return service.getAllDevelopmentIndexes();
    }

    @DeleteMapping("/{id}")
    Boolean deleteDevelopmentIndexById(@PathVariable("id") Long id) {
        return service.deleteDevelopmentIndexById(id);
    }

}
