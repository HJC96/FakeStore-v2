package com.fakeapi.FakeStore.product.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RatingTest {

    @Test
    void Rating_생성_성공() {
        Rating rating = new Rating(4.5, 100);
        assertNotNull(rating);
        assertEquals(4.5, rating.getRate());
        assertEquals(100, rating.getCount());
    }

    @Test
    void Rate가_null일_경우_예외_발생() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> {
            new Rating(null, 100);
        });
        assertEquals("Rating rate cannot be null.", exception.getMessage());
    }

    @Test
    void Count가_null일_경우_예외_발생() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> {
            new Rating(4.5, null);
        });
        assertEquals("Rating count cannot be null.", exception.getMessage());
    }

    @Test
    void Rate가_음수일_경우_예외_발생() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Rating(-1.0, 100);
        });
        assertEquals("Rating rate must be zero or positive.", exception.getMessage());
    }

    @Test
    void Count가_음수일_경우_예외_발생() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Rating(4.5, -1);
        });
        assertEquals("Rating count must be zero or positive.", exception.getMessage());
    }
}