package com.devlife.profile_service.mapper;

import com.devlife.profile_service.dto.PersonalSettingDto;
import com.devlife.profile_service.entity.PersonalSetting;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PersonalSettingMapper {

    private final ModelMapper mapper;
    public PersonalSetting convertToEntity(PersonalSettingDto personalSettingDto) {
        return mapper.map(personalSettingDto, PersonalSetting.class);
    }
    public PersonalSettingDto convertToDto(PersonalSetting personalSettingEntity) {
        return mapper.map(personalSettingEntity, PersonalSettingDto.class);
    }

}
