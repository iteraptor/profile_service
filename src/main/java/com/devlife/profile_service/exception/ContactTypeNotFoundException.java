package com.devlife.profile_service.exception;

public class ContactTypeNotFoundException extends RequestApiException{

    public ContactTypeNotFoundException(Integer id) {
        super("ContactType not found by id = " + id);
    }

}
