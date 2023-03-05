package com.devlife.profile_service.controller.v1;

import com.devlife.profile_service.dto.UserDto;
import com.devlife.profile_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/user")
public class UserController {

    private final UserService service;

    @PutMapping
    Long addAuthorization(@RequestBody UserDto userDto) {
        return service.addAuthorization(userDto);
    }

    @GetMapping("/{id}")
    UserDto getAuthorization(@PathVariable("id") Long id) {
        return service.getAuthorization(id);
    }

    @GetMapping
    List<UserDto> getAllAuthorizations() {
        return service.getAllAuthorizations();
    }

    @DeleteMapping("/{id}")
    Boolean deleteAuthorizationById(@PathVariable("id") Long id) {
        return service.deleteAuthorizationById(id);
    }

}
