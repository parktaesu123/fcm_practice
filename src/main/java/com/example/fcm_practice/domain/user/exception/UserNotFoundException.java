package com.example.fcm_practice.domain.user.exception;

import com.example.fcm_practice.global.error.exception.ErrorCode;
import com.example.fcm_practice.global.error.exception.FcmPracticeException;

public class UserNotFoundException extends FcmPracticeException {
    public static final FcmPracticeException EXCEPTION = new UserNotFoundException();

    public UserNotFoundException() {
        super(ErrorCode.USER_NOT_FOUND);
    }
}
