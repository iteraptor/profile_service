package com.devlife.profile_service.controller.v1;

import com.devlife.profile_service.dto.ContactInformationDto;
import com.devlife.profile_service.service.ContactInformationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/contactInformation")
public class ContactInformationController {

    private final ContactInformationService service;

    @PreAuthorize("hasAuthority('ROOT')")
    @PutMapping
    Long addContactInformation(@RequestBody ContactInformationDto contactInformationDto) {
        return service.addContactInformation(contactInformationDto);
    }

    @PreAuthorize("hasAuthority('ROOT')")
    @GetMapping("/{id}")
    ContactInformationDto getContactInformation(@PathVariable("id") Long id) {
        return (service.getContactInformation(id));
    }

    @PreAuthorize("hasAuthority('ROOT')")
    @GetMapping
    List<ContactInformationDto> getAllContactInformation() {
        return service.getAllContactInformation();
    }

    @PreAuthorize("hasAuthority('ROOT')")
    @DeleteMapping("/{id}")
    Boolean deleteContactInformationById(@PathVariable("id") Long id) {
        return service.deleteContactInformationById(id);
    }

}
