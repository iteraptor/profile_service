package com.devlife.profile_service.exception;

import java.util.Arrays;

public class CountryNotFoundException extends IllegalArgumentException{
    public CountryNotFoundException(String... s) {
        super("Country not found \n " + Arrays.toString(s));
    }
}
