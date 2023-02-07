package com.devlife.profile_service.controller.v1;

import com.devlife.profile_service.dto.ProfileDto;
import com.devlife.profile_service.dto.apiRequestDto.InitProfileReq;
import com.devlife.profile_service.service.ProfileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @PutMapping("/init")
    @Operation(summary = "Init profile while authorization", tags = {"profile"})
    ResponseEntity<ProfileDto> initProfile(
            @Parameter(description = "Authorization request", required = true, name = "initProfileReq")
            @Valid @RequestBody InitProfileReq initProfileReq
    ) {
        return ResponseEntity.ok(service.profileInit(initProfileReq));
    }

}
