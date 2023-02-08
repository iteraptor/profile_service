package com.devlife.profile_service.exception;

public class RequestApiNotFoundException extends RuntimeException{

    public RequestApiNotFoundException(String message) {
        super(message);
    }
}
