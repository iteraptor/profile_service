package com.devlife.profile_service.utils;

import com.devlife.profile_service.ContactType;

import javax.persistence.AttributeConverter;

public class ContactTypeConverter implements AttributeConverter<ContactType, Integer> {
    @Override
    public Integer convertToDatabaseColumn(ContactType attribute) {
        return attribute.getValue();
    }

    @Override
    public ContactType convertToEntityAttribute(Integer dbData) {
        return ContactType.getByValue(dbData);
    }
}
