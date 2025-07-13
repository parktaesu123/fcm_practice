package com.example.fcm_practice.global.security.auth;

import com.example.fcm_practice.domain.user.domain.User;
import com.example.fcm_practice.domain.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String accountId) {
        User user = userRepository.findByAccountId(accountId)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found"));

        return new CustomUserDetails(user);
    }
}
