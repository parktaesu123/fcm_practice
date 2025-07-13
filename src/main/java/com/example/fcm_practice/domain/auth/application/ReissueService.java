package com.example.fcm_practice.domain.auth.application;

import com.example.fcm_practice.domain.auth.domain.RefreshToken;
import com.example.fcm_practice.domain.auth.domain.repository.RefreshTokenRepository;
import com.example.fcm_practice.domain.auth.exception.RefreshTokenNotFoundException;
import com.example.fcm_practice.domain.auth.presentation.dto.request.RefreshTokenRequest;
import com.example.fcm_practice.domain.auth.presentation.dto.response.TokenResponse;
import com.example.fcm_practice.global.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@RequiredArgsConstructor
public class ReissueService {
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public TokenResponse reissue(RefreshTokenRequest request) {

        RefreshToken refreshToken = refreshTokenRepository.findByToken(request.getToken())
                .orElseThrow(() -> RefreshTokenNotFoundException.EXCEPTION);

        return jwtTokenProvider.receiveToken(refreshToken.getAccountId());
    }
}
