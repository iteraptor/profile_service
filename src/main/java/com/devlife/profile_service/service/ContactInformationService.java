package com.devlife.profile_service.service;

import com.devlife.profile_service.dto.ContactInformationDto;
import com.devlife.profile_service.entity.ContactInformation;
import com.devlife.profile_service.mapper.ContactInformationMapper;
import com.devlife.profile_service.repository.ContactInformationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContactInformationService {

    private final ContactInformationRepository contactInformationRepository;
    private final ContactInformationMapper mapper;

    public Long addContactInformation(ContactInformationDto contactInformation) {
        ContactInformation saveContactInformation = contactInformationRepository.save(mapper.convertToEntity(contactInformation));
        if (saveContactInformation != null) {
            return saveContactInformation.getId();
        }
        return null;
    }

    public ContactInformationDto getContactInformation(Long id) {
        ContactInformation contactInformation = contactInformationRepository.getById(id);
        return mapper.convertToDto(contactInformation);
    }

    public List<ContactInformationDto> getAllContactInformation() {
        List<ContactInformation> contactInformationList = contactInformationRepository.findAll();
        return contactInformationList.stream().map(mapper::convertToDto).collect(Collectors.toList());
    }

    public Boolean deleteContactInformationById(Long id) {
        contactInformationRepository.deleteById(id);
        return !contactInformationRepository.existsById(id);
    }

}
