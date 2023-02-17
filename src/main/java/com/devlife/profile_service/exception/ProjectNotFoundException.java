package com.devlife.profile_service.exception;

import java.util.Arrays;

public class ProjectNotFoundException extends RequestApiNotFoundException {

    public ProjectNotFoundException(String... s) {
        super("Project not found \n " + Arrays.toString(s));
    }

}
