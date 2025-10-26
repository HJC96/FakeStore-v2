package com.fakeapi.FakeStore.member.domain;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GeoLocationTest {

    @Test
    void 지리적_위치_생성_성공() {
        GeoLocation geoLocation = new GeoLocation("37.5665", "126.9780");

        assertNotNull(geoLocation);
        assertEquals("37.5665", geoLocation.getLat());
        assertEquals("126.9780", geoLocation.getLongitude());
    }

    @Test
    void Latitude가_null일_경우_예외_발생() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> {
            new GeoLocation(null, "126.9780");
        });
        assertEquals("Latitude cannot be null.", exception.getMessage());
    }

    @Test
    void Latitude가_빈_문자열일_경우_예외_발생() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new GeoLocation(" ", "126.9780");
        });
        assertEquals("Latitude cannot be blank.", exception.getMessage());
    }

    @Test
    void 경도가_null일_경우_예외_발생() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> {
            new GeoLocation("37.5665", null);
        });
        assertEquals("Longitude cannot be null.", exception.getMessage());
    }

    @Test
    void 경도가_빈_문자열일_경우_예외_발생() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new GeoLocation("37.5665", " ");
        });
        assertEquals("Longitude cannot be blank.", exception.getMessage());
    }
}
