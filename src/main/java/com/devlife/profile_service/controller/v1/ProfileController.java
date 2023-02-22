package com.devlife.profile_service.controller.v1;

import com.devlife.profile_service.dto.ProfileDto;
import com.devlife.profile_service.dto.apiRequestDto.InitProfileReq;
import com.devlife.profile_service.dto.apiRequestDto.UpdateProfileByProfileIdReq;
import com.devlife.profile_service.service.ProfileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/profile")
@Validated
public class ProfileController {

    private final ProfileService service;

    @PutMapping
    @Operation(summary = "Add or update profile", tags = {"profile"})
    ResponseEntity<ProfileDto> addProfile(@RequestBody ProfileDto profileDto) {
        return ResponseEntity.ok(service.addProfile(profileDto));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get profile by Id", tags = {"profile"})
    ResponseEntity<ProfileDto> getProfile(
            @Parameter(name = "id", description = "Project ID", required = true)
            @PathVariable("id") @NotNull(message = "ID must be!") Long id) {
        return ResponseEntity.ok(service.getProfile(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update profile by profileId", tags = {"profile"})
    ResponseEntity<ProfileDto> updateProfile(
            @Parameter(description = "profile id", required = true, name = "profile id")
            @PathVariable("id") @NotNull(message = "ID must be!") Long id,
            @Parameter(description = "update data", required = true, name = "updateProfileByProfileIdReq")
            @Valid @RequestBody
            @io.swagger.v3.oas.annotations.parameters.RequestBody
            UpdateProfileByProfileIdReq updateProfileByProfileIdReq
    ) {
        return ResponseEntity.ok(service.updateProfileByProfileId(id, updateProfileByProfileIdReq));
    }

    @GetMapping("/userid/{id}")
    @Operation(summary = "Get profile by userId", tags = {"profile"})
    ResponseEntity<ProfileDto> getProfileByUserId(
            @Parameter(name = "user id", description = "User ID from prf_user table", required = true)
            @PathVariable("id") @NotNull(message = "ID must be!") Long id) {
        return ResponseEntity.ok(service.getProfileByUserId(id));
    }

    @GetMapping
    @Operation(summary = "Get list of profiles", tags = {"profile"})
    ResponseEntity<List<ProfileDto>> getAllProfiles() {
        return ResponseEntity.ok(service.getAllProfiles());
    }

    @PreAuthorize("hasAuthority('ROOT')")
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete profile by id", tags = {"profile"})
    Boolean deleteProfileById(
            @Parameter(name = "id", description = "Profile ID", required = true)
            @PathVariable("id") @NotNull(message = "ID must be!") Long id) {
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
