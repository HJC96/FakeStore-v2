package com.fakeapi.FakeStore.common.config;

import com.fakeapi.FakeStore.common.security.jwt.filter.JwtAuthenticationFilter;
import com.fakeapi.FakeStore.common.security.jwt.provider.JwtAuthenticationProvider;
import com.fakeapi.FakeStore.common.security.jwt.service.RedisBlacklistService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class AuthenticationManagerConfig extends AbstractHttpConfigurer<AuthenticationManagerConfig, HttpSecurity> {

    private final JwtAuthenticationProvider jwtAuthenticationProvider;
    private final StringRedisTemplate redisTemplate;

    @Override
    public void configure(HttpSecurity builder) throws Exception {
        AuthenticationManager authenticationManager = builder.getSharedObject(AuthenticationManager.class);

        builder.addFilterBefore(
                        new JwtAuthenticationFilter(authenticationManager, new RedisBlacklistService(redisTemplate)),
                        UsernamePasswordAuthenticationFilter.class)
                .authenticationProvider(jwtAuthenticationProvider);
    }
}