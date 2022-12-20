package com.devlife.profile_service.controller.v1;

import com.devlife.profile_service.dto.ContactListDto;
import com.devlife.profile_service.service.ContactListService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/contactList")
public class ContactListController {

    private final ContactListService service;

    @PutMapping
    Long addContactList(@RequestBody ContactListDto contactListDto) {
        return service.addContactList(contactListDto);
    }

    @GetMapping("/{id}")
    ContactListDto getContactList(@PathVariable("id") Long id) {
        return (service.getContactList(id));
    }

    @GetMapping
    List<ContactListDto> getAllContactList() {
        return service.getAllContactList();
    }

    @DeleteMapping("/{id}")
    Boolean deleteContactListById(@PathVariable("id") Long id) {
        return service.deleteContactListById(id);
    }

}
