package com.fakeapi.FakeStore.product.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {

    @Test
    void 카테고리_생성_성공() {
        Category category = new Category("Electronics");
        assertNotNull(category);
        assertEquals("Electronics", category.getName());
    }

    @Test
    void 카테고리_이름이_null일_경우_예외_발생() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> {
            new Category(null);
        });
        assertEquals("Category name cannot be null.", exception.getMessage());
    }

    @Test
    void 카테고리_이름이_빈_문자열일_경우_예외_발생() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Category("");
        });
        assertEquals("Category name cannot be blank.", exception.getMessage());
    }

    @Test
    void 카테고리_이름이_공백_문자열일_경우_예외_발생() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Category("   ");
        });
        assertEquals("Category name cannot be blank.", exception.getMessage());
    }

    @Test
    void 카테고리_이름이_255자를_초과할_경우_예외_발생() {
        String longName = "a".repeat(256);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Category(longName);
        });
        assertEquals("Category name cannot be longer than 255 characters.", exception.getMessage());
    }
}