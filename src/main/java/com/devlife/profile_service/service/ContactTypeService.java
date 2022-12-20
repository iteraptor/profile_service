package com.devlife.profile_service.service;

import com.devlife.profile_service.dto.ContactTypeDto;
import com.devlife.profile_service.entity.ContactType;
import com.devlife.profile_service.mapper.ContactTypeMapper;
import com.devlife.profile_service.repository.ContactTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContactTypeService {

    private final ContactTypeRepository contactTypeRepository;
    private final ContactTypeMapper mapper;

    public Long addContactType(ContactTypeDto contactType) {
        ContactType saveContactType = contactTypeRepository.save(mapper.convertToEntity(contactType));
        if (saveContactType != null) {
            return saveContactType.getId();
        }
        return null;
    }

    public ContactTypeDto getContactType(Long id) {
        ContactType contactType = contactTypeRepository.getById(id);
        return mapper.convertToDto(contactType);
    }

    public List<ContactTypeDto> getAllContactTypes() {
        List<ContactType> contactTypesList = contactTypeRepository.findAll();
        return contactTypesList.stream().map(mapper::convertToDto).collect(Collectors.toList());
    }

    public Boolean deleteContactTypeById(Long id) {
        contactTypeRepository.deleteById(id);
        return !contactTypeRepository.existsById(id);
    }

}
