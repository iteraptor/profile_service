package com.devlife.profile_service.mapper;

import com.devlife.profile_service.dto.ContactTypeDto;
import com.devlife.profile_service.entity.ContactType;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ContactTypeMapper {

    private final ModelMapper mapper;
    public ContactType convertToEntity(ContactTypeDto contactTypeDto) {
        return mapper.map(contactTypeDto, ContactType.class);
    }
    public ContactTypeDto convertToDto(ContactType contactTypeEntity) {
        return mapper.map(contactTypeEntity, ContactTypeDto.class);
    }

}
