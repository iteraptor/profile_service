package com.devlife.profile_service.service;

import com.devlife.profile_service.dto.ContactListDto;
import com.devlife.profile_service.entity.ContactList;
import com.devlife.profile_service.mapper.ContactListMapper;
import com.devlife.profile_service.repository.ContactListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContactListService {

    private final ContactListRepository contactListRepository;
    private final ContactListMapper mapper;

    public Long addContactList(ContactListDto contactList) {
        ContactList saveContactList = contactListRepository.save(mapper.convertToEntity(contactList));
        if (saveContactList != null) {
            return saveContactList.getId();
        }
        return null;
    }

    public ContactListDto getContactList(Long id) {
        ContactList contactList = contactListRepository.getById(id);
        return mapper.convertToDto(contactList);
    }

    public List<ContactListDto> getAllContactList() {
        List<ContactList> contactListList = contactListRepository.findAll();
        return contactListList.stream().map(mapper::convertToDto).collect(Collectors.toList());
    }

    public Boolean deleteContactListById(Long id) {
        contactListRepository.deleteById(id);
        return !contactListRepository.existsById(id);
    }

}
