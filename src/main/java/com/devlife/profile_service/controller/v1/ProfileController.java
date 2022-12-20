package com.devlife.profile_service.controller.v1;

import com.devlife.profile_service.dto.ProfileDto;
import com.devlife.profile_service.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/profile")
public class ProfileController {

    private final ProfileService service;

    @PutMapping
    Long addProfile(@RequestBody ProfileDto profileDto) {
        return service.addProfile(profileDto);
    }

    @GetMapping("/{id}")
    ProfileDto getProfile(@PathVariable("id") Long id) {
        return (service.getProfile(id));
    }

    @GetMapping
    List<ProfileDto> getAllProfiles() {
        return service.getAllProfiles();
    }

    @DeleteMapping("/{id}")
    Boolean deleteProfileById(@PathVariable("id") Long id) {
        return service.deleteProfileById(id);
    }

}
