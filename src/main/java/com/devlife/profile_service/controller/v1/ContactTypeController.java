package com.devlife.profile_service.controller.v1;

import com.devlife.profile_service.dto.ContactTypeDto;
import com.devlife.profile_service.service.ContactTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/contactType")
public class ContactTypeController {

    private final ContactTypeService service;

    @PutMapping
    Long addContactType(@RequestBody ContactTypeDto contactTypeDto) {
        return service.addContactType(contactTypeDto);
    }

    @GetMapping("/{id}")
    ContactTypeDto getContactType(@PathVariable("id") Long id) {
        return (service.getContactType(id));
    }

    @GetMapping
    List<ContactTypeDto> getAllContactTypes() {
        return service.getAllContactTypes();
    }

    @DeleteMapping("/{id}")
    Boolean deleteContactTypeById(@PathVariable("id") Long id) {
        return service.deleteContactTypeById(id);
    }

}
