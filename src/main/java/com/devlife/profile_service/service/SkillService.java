package com.devlife.profile_service.service;

import com.devlife.profile_service.dto.SkillDto;
import com.devlife.profile_service.entity.Skill;
import com.devlife.profile_service.mapper.SkillMapper;
import com.devlife.profile_service.repository.SkillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SkillService {

    private final SkillRepository skillRepository;
    private final SkillMapper mapper;

    public Long addSkill(SkillDto skill) {
        Skill saveSkill = skillRepository.save(mapper.convertToEntity(skill));
        if (saveSkill != null) {
            return saveSkill.getId();
        }
        return null;
    }

    public SkillDto getSkill(Long id) {
        Skill skill = skillRepository.getById(id);
        return mapper.convertToDto(skill);
    }

    public List<SkillDto> getAllSkills() {
        List<Skill> skillsList = skillRepository.findAll();
        return skillsList.stream().map(mapper::convertToDto).collect(Collectors.toList());
    }

    public Boolean deleteSkillById(Long id) {
        skillRepository.deleteById(id);
        return !skillRepository.existsById(id);
    }

}
