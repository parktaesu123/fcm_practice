package com.example.fcm_practice.domain.fcm.dto;

import com.example.fcm_practice.domain.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class FcmRequest {
    private List<User> user;
    private String title;
    private String body;
}
