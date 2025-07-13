package com.example.fcm_practice.global.error.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FcmPracticeException extends RuntimeException {
    private final ErrorCode errorCode;
}
