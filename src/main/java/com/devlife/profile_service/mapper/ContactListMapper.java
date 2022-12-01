package com.devlife.profile_service.mapper;

import com.devlife.profile_service.dto.ContactListDto;
import com.devlife.profile_service.entity.ContactList;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ContactListMapper {

    private final ModelMapper mapper;
    public ContactList convertToEntity(ContactListDto contactListDto) {
        return mapper.map(contactListDto, ContactList.class);
    }
    public ContactListDto convertToDto(ContactList contactListEntity) {
        return mapper.map(contactListEntity, ContactListDto.class);
    }

}
