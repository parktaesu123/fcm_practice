package com.example.fcm_practice.domain.auth.presentation.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class LoginRequest {
    @NotBlank
    private String accountId;

    @NotBlank
    private String password;
}
