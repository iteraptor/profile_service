package com.devlife.profile_service.exception;

import java.util.Arrays;

public class ProfileNotFoundException extends RequestApiNotFoundException {

    public ProfileNotFoundException(String... s) {
        super("Profile not found \n " + Arrays.toString(s));
    }

}
