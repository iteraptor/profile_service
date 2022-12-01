package com.devlife.profile_service.controller.v1;

import com.devlife.profile_service.dto.GenderDto;
import com.devlife.profile_service.dto.PersonalSettingDto;
import com.devlife.profile_service.service.GenderService;
import com.devlife.profile_service.service.PersonalSettingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/")
public class PersonalSettingController {

    private final PersonalSettingService service;

    @PutMapping("personalSetting")
    Long addPersonalSetting(@RequestBody PersonalSettingDto personalSettingDto) {
        return service.addPersonalSetting(personalSettingDto);
    }

    @GetMapping("personalSetting/{id}")
    PersonalSettingDto getPersonalSetting(@PathVariable("id") Long id) {
        return (service.getPersonalSetting(id));
    }

    @GetMapping("personalSetting")
    List<PersonalSettingDto> getAllPersonalSettings() {
        return service.getAllPersonalSettings();
    }

    @DeleteMapping("personalSetting/{id}")
    Boolean deletePersonalSettingById(@PathVariable("id") Long id) {
        return service.deletePersonalSettingById(id);
    }

}
