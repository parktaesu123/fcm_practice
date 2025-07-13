package com.example.fcm_practice.infrastructure.fcm;

import com.example.fcm_practice.domain.fcm.domain.FcmMessage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "fcm", url = "/${firebase.messaging.url.host}")
public interface FcmClient {

    @PostMapping("/v1/projects/${external.fcm.project-id}/messages:send")
    void sendMessage(
            @RequestHeader("Authorization") String authHeader,
            @RequestBody FcmMessage message
    );
}
