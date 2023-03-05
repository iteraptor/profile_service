package com.devlife.profile_service.exception;

import java.util.Arrays;

public class ProjectNotFoundException extends RequestApiNotFoundException {

    public ProjectNotFoundException(String field, Object... s) {
        super("Project not found by " + field + "\n " + Arrays.toString(s));
    }

}
