package com.devlife.profile_service.controller.v1;

import com.devlife.profile_service.dto.AvatarDto;
import com.devlife.profile_service.service.AvatarService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/avatar")
public class AvatarController {

    private final AvatarService service;

    @PreAuthorize("hasAuthority('ROOT')")
    @PutMapping
    Long addAvatar(@RequestBody AvatarDto avatarDto) {
        return service.addAvatar(avatarDto);
    }

    @PreAuthorize("hasAuthority('ROOT')")
    @GetMapping("/{id}")
    AvatarDto getAvatar(@PathVariable("id") Long id) {
        return (service.getAvatar(id));
    }

    @PreAuthorize("hasAuthority('ROOT')")
    @GetMapping
    List<AvatarDto> getAllAvatars() {
        return service.getAllAvatars();
    }

    @PreAuthorize("hasAuthority('ROOT')")
    @DeleteMapping("/{id}")
    Boolean deleteAvatarById(@PathVariable("id") Long id) {
        return service.deleteAvatarById(id);
    }

}
