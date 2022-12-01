package com.devlife.profile_service.controller.v1;

import com.devlife.profile_service.dto.AvatarDto;
import com.devlife.profile_service.service.AvatarService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/")
public class AvatarController {

    private final AvatarService service;

    @PutMapping("avatar")
    Long addAvatar(@RequestBody AvatarDto avatarDto) {
        return service.addAvatar(avatarDto);
    }

    @GetMapping("avatar/{id}")
    AvatarDto getAvatar(@PathVariable("id") Long id) {
        return (service.getAvatar(id));
    }

    @GetMapping("avatar")
    List<AvatarDto> getAllAvatars() {
        return service.getAllAvatars();
    }

    @DeleteMapping("avatar/{id}")
    Boolean deleteAvatarById(@PathVariable("id") Long id) {
        return service.deleteAvatarById(id);
    }

}
