package com.fakeapi.FakeStore.member.domain;

import jakarta.persistence.Embeddable;

@Embeddable
public class Name {
    private String firstname;
    private String lastname;
}
