package com.devlife.profile_service.exception;

public class ProjectNotFoundException extends RequestApiNotFoundException {

    public ProjectNotFoundException(Long id) {
        super("Project not found by id = " + id);
    }

}
