package com.example.fcm_practice.domain.auth.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;

@RedisHash
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RefreshToken {

    @Id
    private String accountId;

    @Indexed
    private String token;

    @TimeToLive
    private Long timeToLive;
}
