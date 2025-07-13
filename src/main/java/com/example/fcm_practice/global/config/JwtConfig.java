package com.example.fcm_practice.global.config;

import com.example.fcm_practice.global.security.jwt.JwtProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(JwtProperties.class)
public class JwtConfig {
    //JwtProperties 빈으로 등록
}
