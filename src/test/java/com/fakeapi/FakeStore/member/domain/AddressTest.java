package com.fakeapi.FakeStore.member.domain;

import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddressTest {

    private GeoLocation testGeoLocation;

    @BeforeEach
    void setUp() {
        testGeoLocation = new GeoLocation("37.5665", "126.9780");
    }

    @Test
    void 주소_생성_성공() {
        Address address = new Address("Seoul", "123 Main St", 101, "12345", testGeoLocation);

        assertNotNull(address);
        assertEquals("Seoul", address.getCity());
        assertEquals("123 Main St", address.getStreet());
        assertEquals(101, address.getNumber());
        assertEquals("12345", address.getZipcode());
        assertEquals(testGeoLocation, address.getGeolocation());
    }

    @Test
    void City가_null일_경우_예외_발생() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> {
            new Address(null, "123 Main St", 101, "12345", testGeoLocation);
        });
        assertEquals("City cannot be null.", exception.getMessage());
    }

    @Test
    void City가_빈_문자열일_경우_예외_발생() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Address(" ", "123 Main St", 101, "12345", testGeoLocation);
        });
        assertEquals("City cannot be blank.", exception.getMessage());
    }

    @Test
    void Street이_null일_경우_예외_발생() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> {
            new Address("Seoul", null, 101, "12345", testGeoLocation);
        });
        assertEquals("Street cannot be null.", exception.getMessage());
    }

    @Test
    void Street이_빈_문자열일_경우_예외_발생() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Address("Seoul", " ", 101, "12345", testGeoLocation);
        });
        assertEquals("Street cannot be blank.", exception.getMessage());
    }

    @Test
    void Zipcode가_null일_경우_예외_발생() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> {
            new Address("Seoul", "123 Main St", 101, null, testGeoLocation);
        });
        assertEquals("Zipcode cannot be null.", exception.getMessage());
    }

    @Test
    void Zipcode가_빈_문자열일_경우_예외_발생() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Address("Seoul", "123 Main St", 101, " ", testGeoLocation);
        });
        assertEquals("Zipcode cannot be blank.", exception.getMessage());
    }

    @Test
    void 지리적_위치가_null일_경우_예외_발생() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> {
            new Address("Seoul", "123 Main St", 101, "12345", null);
        });
        assertEquals("Geolocation cannot be null.", exception.getMessage());
    }
}
