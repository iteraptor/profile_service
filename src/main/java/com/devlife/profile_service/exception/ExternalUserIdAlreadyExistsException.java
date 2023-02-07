package com.devlife.profile_service.exception;

public class ExternalUserIdAlreadyExistsException extends RequestApiException{
    public ExternalUserIdAlreadyExistsException(Long externalId) {
        super("External user id [" + externalId + "] already exists");
    }
}
