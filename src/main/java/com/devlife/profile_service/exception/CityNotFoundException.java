package com.devlife.profile_service.exception;

import java.util.Arrays;

public class CityNotFoundException extends IllegalArgumentException{
    public CityNotFoundException(String... s) {
        super("City not found \n " + Arrays.toString(s));
    }
}
