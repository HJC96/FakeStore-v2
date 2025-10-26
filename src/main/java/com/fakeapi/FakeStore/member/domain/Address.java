package com.fakeapi.FakeStore.member.domain;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Address {
    private String city;
    private String street;
    private int number;
    private String zipcode;

    @Embedded
    private GeoLocation geolocation;

    public Address(String city, String street, int number, String zipcode, GeoLocation geolocation) {
        this.city = Objects.requireNonNull(city, "City cannot be null.");
        if (city.isBlank()) {
            throw new IllegalArgumentException("City cannot be blank.");
        }
        this.street = Objects.requireNonNull(street, "Street cannot be null.");
        if (street.isBlank()) {
            throw new IllegalArgumentException("Street cannot be blank.");
        }
        this.zipcode = Objects.requireNonNull(zipcode, "Zipcode cannot be null.");
        if (zipcode.isBlank()) {
            throw new IllegalArgumentException("Zipcode cannot be blank.");
        }
        this.geolocation = Objects.requireNonNull(geolocation, "Geolocation cannot be null.");
        this.number = number;
    }
}

