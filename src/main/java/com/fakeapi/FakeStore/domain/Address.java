package com.fakeapi.FakeStore.domain;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;

@Embeddable
public class Address {
    private String city;
    private String street;
    private int number;
    private String zipcode;

    @Embedded
    private GeoLocation geolocation;
}

