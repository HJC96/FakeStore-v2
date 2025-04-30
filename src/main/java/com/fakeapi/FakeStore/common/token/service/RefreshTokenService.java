package com.fakeapi.FakeStore.common.token.service;

import com.fakeapi.FakeStore.common.token.domain.RefreshToken;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public interface RefreshTokenService {

    RefreshToken addRefreshToken(RefreshToken refreshToken);

    void deleteRefreshToken(String refreshToken);

    Optional<RefreshToken> findRefreshToken(String refreshToken);

}
