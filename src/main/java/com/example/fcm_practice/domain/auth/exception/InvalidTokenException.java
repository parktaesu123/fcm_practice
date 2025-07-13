package com.example.fcm_practice.domain.auth.exception;

import com.example.fcm_practice.global.error.exception.ErrorCode;
import com.example.fcm_practice.global.error.exception.FcmPracticeException;

public class InvalidTokenException extends FcmPracticeException {
    public static final FcmPracticeException EXCEPTION = new InvalidTokenException();

    public InvalidTokenException() {
        super(ErrorCode.INVALID_TOKEN);
    }
}
