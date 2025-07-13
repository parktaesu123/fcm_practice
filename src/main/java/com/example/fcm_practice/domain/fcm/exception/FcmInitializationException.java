package com.example.fcm_practice.domain.fcm.exception;

import com.example.fcm_practice.global.error.exception.ErrorCode;
import com.example.fcm_practice.global.error.exception.FcmPracticeException;

public class FcmInitializationException extends FcmPracticeException {
    public static final FcmPracticeException EXCEPTION = new FcmInitializationException();

    private FcmInitializationException() {
        super(ErrorCode.FCM_INITIALIZATION);
    }
}
