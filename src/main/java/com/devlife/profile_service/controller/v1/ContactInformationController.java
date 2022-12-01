package com.devlife.profile_service.controller.v1;

import com.devlife.profile_service.dto.ContactInformationDto;
import com.devlife.profile_service.service.ContactInformationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/")
public class ContactInformationController {

    private final ContactInformationService service;

    @PutMapping("contactInformation")
    Long addContactInformation(@RequestBody ContactInformationDto contactInformationDto) {
        return service.addContactInformation(contactInformationDto);
    }

    @GetMapping("contactInformation/{id}")
    ContactInformationDto getContactInformation(@PathVariable("id") Long id) {
        return (service.getContactInformation(id));
    }

    @GetMapping("contactInformation")
    List<ContactInformationDto> getAllContactInformation() {
        return service.getAllContactInformation();
    }

    @DeleteMapping("contactInformation/{id}")
    Boolean deleteContactInformationById(@PathVariable("id") Long id) {
        return service.deleteContactInformationById(id);
    }

}
