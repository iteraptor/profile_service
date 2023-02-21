package com.devlife.profile_service.exception;

import java.util.Arrays;

public class GenderNotFoundException extends IllegalArgumentException{
    public GenderNotFoundException(String... s) {
        super("Gender not found \n " + Arrays.toString(s));
    }
}
