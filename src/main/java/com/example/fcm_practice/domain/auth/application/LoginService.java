package com.example.fcm_practice.domain.auth.application;

import com.example.fcm_practice.domain.auth.exception.PasswordMismatchException;
import com.example.fcm_practice.domain.auth.presentation.dto.request.LoginRequest;
import com.example.fcm_practice.domain.auth.presentation.dto.response.TokenResponse;
import com.example.fcm_practice.domain.user.domain.User;
import com.example.fcm_practice.domain.user.domain.repository.UserRepository;
import com.example.fcm_practice.domain.user.exception.UserNotFoundException;
import com.example.fcm_practice.global.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Transactional
    public TokenResponse login(LoginRequest request) {

        User user = userRepository.findByAccountId(request.getAccountId())
                .orElseThrow(()-> UserNotFoundException.EXCEPTION);


        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw PasswordMismatchException.EXCEPTION;
        }

        return jwtTokenProvider.receiveToken(request.getAccountId());
    }
}
