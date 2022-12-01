package com.devlife.profile_service.controller.v1;

import com.devlife.profile_service.dto.AuthorizationDto;
import com.devlife.profile_service.service.AuthorizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/")
public class AuthorizationController {

    private final AuthorizationService service;

    @PutMapping("auth")
    Long addAuthorization(@RequestBody AuthorizationDto authorizationDto) {
        return service.addAuthorization(authorizationDto);
    }

    @GetMapping("auth/{id}")
    AuthorizationDto getAuthorization(@PathVariable("id") Long id) {
        return (service.getAuthorization(id));
    }

    @GetMapping("auth")
    List<AuthorizationDto> getAllAuthorizations() {
        return service.getAllAuthorizations();
    }

    @DeleteMapping("auth/{id}")
    Boolean deleteAuthorizationById(@PathVariable("id") Long id) {
        return service.deleteAuthorizationById(id);
    }

}
