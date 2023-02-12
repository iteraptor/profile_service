package com.devlife.profile_service.enums;

import com.devlife.profile_service.exception.ContactTypeNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@AllArgsConstructor
@Getter
public enum ContactType {
    EMAIL(1),
    PHONE_NUMBER(2),
    TELEGRAM(3),
    SKYPE(4);

    private final Integer value;

    public static ContactType getByValue(Integer value) {
        return Arrays.stream(ContactType.values())
                .filter(t -> t.getValue().equals(value)).findAny().orElseThrow(() -> new ContactTypeNotFoundException(value));
    }
}
