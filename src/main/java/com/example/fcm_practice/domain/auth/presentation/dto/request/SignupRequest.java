package com.example.fcm_practice.domain.auth.presentation.dto.request;

import com.example.fcm_practice.domain.user.domain.enums.Role;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class SignupRequest {

    @NotBlank
    private String userName;

    @NotBlank
    private Integer age;

    @NotBlank
    private String accountId;

    @NotBlank
    private String password;

    private Role role;
}
