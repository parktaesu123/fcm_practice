package com.example.fcm_practice.domain.auth.exception;

import com.example.fcm_practice.global.error.exception.ErrorCode;
import com.example.fcm_practice.global.error.exception.FcmPracticeException;

public class ExpiredTokenException extends FcmPracticeException {
    public static final FcmPracticeException EXCEPTION = new ExpiredTokenException();

    public ExpiredTokenException() {
        super(ErrorCode.EXPIRED_TOKEN);
    }
}
