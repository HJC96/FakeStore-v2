package com.fakeapi.FakeStore.product.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    private Category testCategory;
    private Rating testRating;

    @BeforeEach
    void setUp() {
        testCategory = new Category("Electronics");
        testRating = new Rating(4.0, 50);
    }

    @Test
    void Product_생성_성공_ID_없음() {
        Product product = new Product(
                "Laptop",
                1200.0,
                "Powerful laptop for work and gaming.",
                testCategory,
                "http://example.com/laptop.jpg",
                testRating
        );

        assertNotNull(product);
        assertNull(product.getId());
        assertEquals("Laptop", product.getTitle());
        assertEquals(1200.0, product.getPrice());
        assertEquals("Powerful laptop for work and gaming.", product.getDescription());
        assertEquals(testCategory, product.getCategory());
        assertEquals("http://example.com/laptop.jpg", product.getImage());
        assertEquals(testRating, product.getRating());
    }

    @Test
    void Product_생성_성공_ID_포함() {
        Product product = new Product(
                1L,
                "Smartphone",
                800.0,
                "Latest model smartphone with advanced features.",
                testCategory,
                "http://example.com/smartphone.jpg",
                testRating
        );

        assertNotNull(product);
        assertEquals(1L, product.getId());
        assertEquals("Smartphone", product.getTitle());
        assertEquals(800.0, product.getPrice());
        assertEquals("Latest model smartphone with advanced features.", product.getDescription());
        assertEquals(testCategory, product.getCategory());
        assertEquals("http://example.com/smartphone.jpg", product.getImage());
        assertEquals(testRating, product.getRating());
    }

    @Test
    void Title이_null일_경우_예외_발생() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> {
            new Product(null, 100.0, "desc", testCategory, "image.jpg", testRating);
        });
        assertEquals("Product title cannot be null.", exception.getMessage());
    }

    @Test
    void Title이_빈_문자열일_경우_예외_발생() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Product("", 100.0, "desc", testCategory, "image.jpg", testRating);
        });
        assertEquals("Product title cannot be blank.", exception.getMessage());
    }

    @Test
    void Title이_255자를_초과할_경우_예외_발생() {
        String longTitle = "a".repeat(256);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Product(longTitle, 100.0, "desc", testCategory, "image.jpg", testRating);
        });
        assertEquals("Product title cannot be longer than 255 characters.", exception.getMessage());
    }

    @Test
    void Price가_null일_경우_예외_발생() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> {
            new Product("Title", null, "desc", testCategory, "image.jpg", testRating);
        });
        assertEquals("Product price cannot be null.", exception.getMessage());
    }

    @Test
    void Price가_음수일_경우_예외_발생() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Product("Title", -10.0, "desc", testCategory, "image.jpg", testRating);
        });
        assertEquals("Product price must be zero or positive.", exception.getMessage());
    }

    @Test
    void Description이_null일_경우_예외_발생() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> {
            new Product("Title", 100.0, null, testCategory, "image.jpg", testRating);
        });
        assertEquals("Product description cannot be null.", exception.getMessage());
    }

    @Test
    void Description이_빈_문자열일_경우_예외_발생() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Product("Title", 100.0, "", testCategory, "image.jpg", testRating);
        });
        assertEquals("Product description cannot be blank.", exception.getMessage());
    }

    @Test
    void Image가_null일_경우_예외_발생() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> {
            new Product("Title", 100.0, "desc", testCategory, null, testRating);
        });
        assertEquals("Product image cannot be null.", exception.getMessage());
    }

    @Test
    void Image가_빈_문자열일_경우_예외_발생() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Product("Title", 100.0, "desc", testCategory, "", testRating);
        });
        assertEquals("Product image cannot be blank.", exception.getMessage());
    }

    @Test
    void Category가_null일_경우_예외_발생() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> {
            new Product("Title", 100.0, "desc", null, "image.jpg", testRating);
        });
        assertEquals("Product category cannot be null.", exception.getMessage());
    }

    @Test
    void Rating이_null이어도_Product_생성_성공() {
        Product product = new Product(
                "Book",
                25.0,
                "A good book.",
                testCategory,
                "http://example.com/book.jpg",
                null // Rating is nullable
        );
        assertNotNull(product);
        assertNull(product.getRating());
    }
}