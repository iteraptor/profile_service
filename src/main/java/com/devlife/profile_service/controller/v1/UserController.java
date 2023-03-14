package com.devlife.profile_service.controller.v1;

import com.devlife.profile_service.dto.UserDto;
import com.devlife.profile_service.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/user")
public class UserController {

    private final UserService service;

    @PreAuthorize("hasAuthority('ROOT')")
    @PutMapping
    Long addUser(@RequestBody UserDto userDto) {
        return service.addUser(userDto);
    }

    @PreAuthorize("hasAuthority('ROOT')")
    @GetMapping("/{id}")
    UserDto getUser(@PathVariable("id") Long id) {
        return service.getUser(id);
    }

    @PreAuthorize("hasAuthority('ROOT')")
    @GetMapping
    List<UserDto> getAllUsers() {
        return service.getAllUsers();
    }

    @PreAuthorize("hasAuthority('ROOT')")
    @DeleteMapping("/{id}")
    Boolean deleteUserById(@PathVariable("id") Long id) {
        return service.deleteUserById(id);
    }

    @Operation(summary = "Get external user id by auth user id from auth service", tags = {"user"})
    @GetMapping("/auth/{id}")
    Long getUserIdByAuthId(@PathVariable("id")
                           @Parameter(name = "id from auth service", required = true)
                           @NotNull Long id) {
        return service.getUserIdByAuthId(id);
    }
}
