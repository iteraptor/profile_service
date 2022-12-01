package com.devlife.profile_service.service;

import com.devlife.profile_service.dto.PersonalSettingDto;
import com.devlife.profile_service.entity.PersonalSetting;
import com.devlife.profile_service.mapper.PersonalSettingMapper;
import com.devlife.profile_service.repository.PersonalSettingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PersonalSettingService {

    private final PersonalSettingRepository personalSettingRepository;
    private final PersonalSettingMapper mapper;

    public Long addPersonalSetting(PersonalSettingDto personalSetting) {
        PersonalSetting savePersonalSetting = personalSettingRepository.save(mapper.convertToEntity(personalSetting));
        if (savePersonalSetting != null) {
            return savePersonalSetting.getId();
        }
        return null;
    }

    public PersonalSettingDto getPersonalSetting(Long id) {
        PersonalSetting personalSetting = personalSettingRepository.getById(id);
        return mapper.convertToDto(personalSetting);
    }

    public List<PersonalSettingDto> getAllPersonalSettings() {
        List<PersonalSetting> personalSettingsList = personalSettingRepository.findAll();
        return personalSettingsList.stream().map(mapper::convertToDto).collect(Collectors.toList());
    }

    public Boolean deletePersonalSettingById(Long id) {
        personalSettingRepository.deleteById(id);
        return !personalSettingRepository.existsById(id);
    }

}
