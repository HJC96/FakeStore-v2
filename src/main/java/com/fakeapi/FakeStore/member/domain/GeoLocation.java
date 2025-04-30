package com.fakeapi.FakeStore.member.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class GeoLocation {
    private String lat;
    private String longitude;

    @Column(name = "longitude")  // "long"은 예약어라 에러남
    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
