package com.devlife.profile_service.exception;

import java.util.Arrays;

public class ProjectUserLinkNotFound extends RequestApiNotFoundException{
    public ProjectUserLinkNotFound(String field, Object... s) {
        super("link between project and profile not found by " + field + "\n " + Arrays.toString(s));
    }
}
