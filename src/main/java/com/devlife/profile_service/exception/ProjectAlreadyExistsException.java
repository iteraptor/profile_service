package com.devlife.profile_service.exception;

public class ProjectAlreadyExistsException extends RequestApiNotFoundException {
    public ProjectAlreadyExistsException(Long id) {
        super("Project with external id [" + id + "] already exists");
    }
}
