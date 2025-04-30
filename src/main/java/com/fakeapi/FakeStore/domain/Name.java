package com.fakeapi.FakeStore.domain;

import jakarta.persistence.Embeddable;

@Embeddable
public class Name {
    private String firstname;
    private String lastname;
}
