package com.fakeapi.FakeStore.common.token.service.impl;

import com.fakeapi.FakeStore.common.token.domain.RefreshToken;
import com.fakeapi.FakeStore.common.token.repository.RefreshTokenRepository;
import com.fakeapi.FakeStore.common.token.service.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class RefreshTokenServiceImpl implements RefreshTokenService {
    @Autowired
    private final RefreshTokenRepository refreshTokenRepository;

    @Override
    public RefreshToken addRefreshToken(RefreshToken refreshToken) {
        return refreshTokenRepository.save(refreshToken);
    }

    @Override
    public Optional<RefreshToken> findRefreshToken(String refreshToken) {
        return refreshTokenRepository.findByValue(refreshToken);
    }

    @Override
    public void deleteRefreshToken(String refreshToken) {
        log.info(String.valueOf(refreshTokenRepository.findByValue(refreshToken).get()));
        refreshTokenRepository.delete(refreshTokenRepository.findByValue(refreshToken).get());
    }
}
