package com.fakeapi.FakeStore.member.domain;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GeoLocation {
    private String lat;
    private String longitude;

    public GeoLocation(String lat, String longitude) {
        this.lat = Objects.requireNonNull(lat, "Latitude cannot be null.");
        if (lat.isBlank()) {
            throw new IllegalArgumentException("Latitude cannot be blank.");
        }
        this.longitude = Objects.requireNonNull(longitude, "Longitude cannot be null.");
        if (longitude.isBlank()) {
            throw new IllegalArgumentException("Longitude cannot be blank.");
        }
    }
}
