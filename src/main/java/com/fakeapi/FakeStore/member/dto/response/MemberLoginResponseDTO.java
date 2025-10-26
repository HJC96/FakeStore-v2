package com.fakeapi.FakeStore.member.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberLoginResponseDTO {
    private String accessToken;
    private String refreshToken;
}
