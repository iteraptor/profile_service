package com.devlife.profile_service.exception;

public class NicknameAlreadyExistsException extends RequestApiNotFoundException {
    public NicknameAlreadyExistsException(String nickname) {
        super("Nickname [" + nickname + "] already exists");
    }
}
