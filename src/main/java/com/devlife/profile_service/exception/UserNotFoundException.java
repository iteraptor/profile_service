package com.devlife.profile_service.exception;

import java.util.Arrays;

public class UserNotFoundException extends RequestApiNotFoundException{
    public UserNotFoundException(String field, Object... s) {
        super("User not found by " + field + "\n " + Arrays.toString(s));
    }
}
