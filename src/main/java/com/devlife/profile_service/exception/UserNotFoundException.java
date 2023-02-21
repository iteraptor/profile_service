package com.devlife.profile_service.exception;

import java.util.Arrays;

public class UserNotFoundException extends IllegalArgumentException{
    public UserNotFoundException(String... s) {
        super("User not found \n " + Arrays.toString(s));
    }
}
