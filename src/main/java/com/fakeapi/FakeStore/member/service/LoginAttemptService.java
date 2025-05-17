package com.fakeapi.FakeStore.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class LoginAttemptService {

    private static final int MAX_ATTEMPT = 5;
    private static final Duration LOCK_DURATION = Duration.ofMinutes(15);

    private final StringRedisTemplate redisTemplate;

    public void recordFailedAttempt(String email) {
        String key = "loginFail:" + email;

        Long attempts = redisTemplate.opsForValue().increment(key);  // 실패 횟수 증가
        if (attempts == 1) {
            redisTemplate.expire(key, LOCK_DURATION); // 첫 실패 시 TTL 설정
        }
    }

    public boolean isBlocked(String email) {
        String key = "loginFail:" + email;

        String value = redisTemplate.opsForValue().get(key);
        if (value == null) return false;

        int count = Integer.parseInt(value);
        return count >= MAX_ATTEMPT;
    }

    public void clearAttempts(String email) {
        redisTemplate.delete("loginFail:" + email);
    }
}