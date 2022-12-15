package com.devlife.profile_service.controller.v1;

import com.devlife.profile_service.dto.SkillDto;
import com.devlife.profile_service.service.SkillService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/skill")
public class SkillController {

    private final SkillService service;

    @PutMapping
    Long addSkill(@RequestBody SkillDto skillDto) {
        return service.addSkill(skillDto);
    }

    @GetMapping("/{id}")
    SkillDto getSkill(@PathVariable("id") Long id) {
        return (service.getSkill(id));
    }

    @GetMapping
    List<SkillDto> getAllSkills() {
        return service.getAllSkills();
    }

    @DeleteMapping("/{id}")
    Boolean deleteSkillById(@PathVariable("id") Long id) {
        return service.deleteSkillById(id);
    }

}
