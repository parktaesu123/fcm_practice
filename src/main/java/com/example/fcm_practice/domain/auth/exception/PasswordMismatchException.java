package com.example.fcm_practice.domain.auth.exception;

import com.example.fcm_practice.global.error.exception.ErrorCode;
import com.example.fcm_practice.global.error.exception.FcmPracticeException;
import com.example.springboot_practice.global.error.exception.ClimException;
import com.example.springboot_practice.global.error.exception.ErrorCode;

public class PasswordMismatchException extends FcmPracticeException {
    public static final FcmPracticeException EXCEPTION = new PasswordMismatchException();

    public PasswordMismatchException() {
        super(ErrorCode.PASSWORD_MISMATCH);
    }
}
