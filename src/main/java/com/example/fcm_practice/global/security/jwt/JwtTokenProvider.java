package com.example.fcm_practice.global.security.jwt;

import com.example.fcm_practice.domain.auth.domain.RefreshToken;
import com.example.fcm_practice.domain.auth.domain.repository.RefreshTokenRepository;
import com.example.fcm_practice.domain.auth.exception.ExpiredTokenException;
import com.example.fcm_practice.domain.auth.exception.InvalidTokenException;
import com.example.fcm_practice.domain.auth.presentation.dto.response.TokenResponse;
import com.example.fcm_practice.domain.user.domain.User;
import com.example.fcm_practice.domain.user.domain.repository.UserRepository;
import com.example.fcm_practice.domain.user.exception.UserNotFoundException;
import com.example.fcm_practice.global.security.auth.CustomUserDetailsService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {
    private final JwtProperties jwtProperties;
    private final UserRepository userRepository;
    private final CustomUserDetailsService customUserDetailsService;
    private final RefreshTokenRepository refreshTokenRepository;

    //access token 생성
    public String createAccessToken(String accountId) {
        Date now = new Date(); //코드를 실행한 시점의 현재 날짜와 시간이 저장(일시적)

        return Jwts.builder()
                .setSubject(accountId) //토큰의 소유자
                .claim("type", "access") //액세스 토큰임을 나타냄
                .setIssuedAt(now) //토큰 발행 시간 정보
                .setExpiration(new Date(now.getTime() + jwtProperties.getAccessExpiration() * 1000)) //토큰의 만료 시간 설정
                .signWith(SignatureAlgorithm.HS512, jwtProperties.getSecretKey()) //HS512 알고리즘, 비밀 키를 Jwtproperties에서 가져옴
                .compact();

    }

    //refresh token 생성

    @Transactional
    public String createRefreshToken(String accountId) {
        Date now = new Date();

        String refreshToken = Jwts.builder()
                .claim("type", "refresh")  //refresh 토큰임을 나타냄
                .setIssuedAt(now)
                .setExpiration(new java.sql.Timestamp(now.getTime() + jwtProperties.getRefreshExpiration() * 1000))
                .signWith(SignatureAlgorithm.HS512, jwtProperties.getSecretKey()) //
                .compact();


        refreshTokenRepository.save(
                RefreshToken.builder()
                        .accountId(accountId)
                        .token(refreshToken)
                        .timeToLive((jwtProperties.getRefreshExpiration()))
                        .build()
        );

        return refreshToken;
    }

    // 토큰에 담겨 있는 userId로 SpringSecurity Authentication 정보를 반환 하는 메서드
    public Authentication getAuthentication(String token) {
        Claims claims = getClaims(token);
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(claims.getSubject());
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public Claims getClaims(String token) {
        try {
            return Jwts
                    .parser() //JWT parser 생성
                    .setSigningKey(jwtProperties.getSecretKey())
                    .parseClaimsJws(token)
                    .getBody();
        }
        catch (ExpiredJwtException E) {
            throw ExpiredTokenException.EXCEPTION;
        }
        catch (Exception E) {
            throw InvalidTokenException.EXCEPTION;
        }
    }

    public TokenResponse receiveToken(String accountId) {

        Date  now = new Date();

        User user = userRepository.findByAccountId(accountId)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        return TokenResponse
                .builder()
                .accessToken(createAccessToken(accountId))
                .refreshToken(createRefreshToken(accountId))
                .accessExpiredAt(new Date(now.getTime() + jwtProperties.getAccessExpiration()))
                .refreshExpiredAt(new Date(now.getTime() + jwtProperties.getRefreshExpiration()))
                .build();
    }

    //HTTP 요청 헤더에서 토큰을 가져오는 메서드
    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(jwtProperties.getHeader());

        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(jwtProperties.getPrefix())
                && bearerToken.length() > jwtProperties.getPrefix().length() + 1) {
            return bearerToken.substring(7);
        }

        return null;
    }


}
