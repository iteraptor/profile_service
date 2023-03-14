package com.devlife.profile_service.controller.v1;

import com.devlife.profile_service.dto.GenderDto;
import com.devlife.profile_service.dto.PersonalSettingDto;
import com.devlife.profile_service.service.GenderService;
import com.devlife.profile_service.service.PersonalSettingService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/personalSetting")
public class PersonalSettingController {

    private final PersonalSettingService service;

    @PreAuthorize("hasAuthority('ROOT')")
    @PutMapping
    Long addPersonalSetting(@RequestBody PersonalSettingDto personalSettingDto) {
        return service.addPersonalSetting(personalSettingDto);
    }

    @PreAuthorize("hasAuthority('ROOT')")
    @GetMapping("/{id}")
    PersonalSettingDto getPersonalSetting(@PathVariable("id") Long id) {
        return (service.getPersonalSetting(id));
    }

    @PreAuthorize("hasAuthority('ROOT')")
    @GetMapping
    List<PersonalSettingDto> getAllPersonalSettings() {
        return service.getAllPersonalSettings();
    }

    @PreAuthorize("hasAuthority('ROOT')")
    @DeleteMapping("/{id}")
    Boolean deletePersonalSettingById(@PathVariable("id") Long id) {
        return service.deletePersonalSettingById(id);
    }

}
