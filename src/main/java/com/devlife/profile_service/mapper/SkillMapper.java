package com.devlife.profile_service.mapper;

import com.devlife.profile_service.dto.SkillDto;
import com.devlife.profile_service.entity.Skill;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SkillMapper {

    private final ModelMapper mapper;
    public Skill convertToEntity(SkillDto skillDto) {
        return mapper.map(skillDto, Skill.class);
    }
    public SkillDto convertToDto(Skill skillEntity) {
        return mapper.map(skillEntity, SkillDto.class);
    }

}
