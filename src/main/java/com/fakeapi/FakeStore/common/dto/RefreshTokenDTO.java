package com.fakeapi.FakeStore.common.dto;

import lombok.Data;

import jakarta.validation.constraints.NotEmpty;

@Data
public class RefreshTokenDTO {
    @NotEmpty
    String refreshToken;

}
