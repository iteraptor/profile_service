package com.devlife.profile_service.exception;

import java.util.Arrays;

public class AvatarNotFoundException extends IllegalArgumentException{
    public AvatarNotFoundException(String... s) {
        super("Avatar not found \n " + Arrays.toString(s));
    }
}
