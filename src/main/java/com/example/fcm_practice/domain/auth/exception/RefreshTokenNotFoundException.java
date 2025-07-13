package com.example.fcm_practice.domain.auth.exception;

import com.example.fcm_practice.global.error.exception.ErrorCode;
import com.example.fcm_practice.global.error.exception.FcmPracticeException;

public class RefreshTokenNotFoundException extends FcmPracticeException {
    public static final FcmPracticeException EXCEPTION = new RefreshTokenNotFoundException();

    public RefreshTokenNotFoundException() {
        super(ErrorCode.REFRESH_TOKEN_NOT_FOUND);
    }
}
