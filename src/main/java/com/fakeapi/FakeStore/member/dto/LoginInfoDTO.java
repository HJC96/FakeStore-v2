package com.fakeapi.FakeStore.member.dto;

import lombok.Data;

@Data
public class LoginInfoDTO {
    private Long memberId;
    private String email;
    private String name;
}
