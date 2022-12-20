package com.devlife.profile_service.mapper;

import com.devlife.profile_service.dto.ContactInformationDto;
import com.devlife.profile_service.entity.ContactInformation;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ContactInformationMapper {

    private final ModelMapper mapper;
    public ContactInformation convertToEntity(ContactInformationDto contactInformationDto) {
        return mapper.map(contactInformationDto, ContactInformation.class);
    }
    public ContactInformationDto convertToDto(ContactInformation contactInformationEntity) {
        return mapper.map(contactInformationEntity, ContactInformationDto.class);
    }

}
