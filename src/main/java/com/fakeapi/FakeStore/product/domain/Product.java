package com.fakeapi.FakeStore.product.domain;


import jakarta.persistence.*;
import lombok.*;
import java.util.Objects;
import org.springframework.util.StringUtils;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Product {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRODUCT_ID")
    private Long id;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "PRICE")
    private Double price;

    @Column(name = "DESCRIPTION", length = 2000)
    private String description;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "CATEGORY_ID", nullable=false)
    private Category category;

    @Column(name = "IMAGE")
    private String image;

    @Embedded
    @Column(name = "RATING", nullable = true)
    private Rating rating;

    public Product(String title, Double price, String description, Category category, String image, Rating rating) {
        this.title = Objects.requireNonNull(title, "Product title cannot be null.");
        if (title.isBlank()) {
            throw new IllegalArgumentException("Product title cannot be blank.");
        }
        if (title.length() > 255) {
            throw new IllegalArgumentException("Product title cannot be longer than 255 characters.");
        }
        this.price = Objects.requireNonNull(price, "Product price cannot be null.");
        if (price < 0) {
            throw new IllegalArgumentException("Product price must be zero or positive.");
        }
        this.description = Objects.requireNonNull(description, "Product description cannot be null.");
        if (description.isBlank()) {
            throw new IllegalArgumentException("Product description cannot be blank.");
        }
        this.image = Objects.requireNonNull(image, "Product image cannot be null.");
        if (image.isBlank()) {
            throw new IllegalArgumentException("Product image cannot be blank.");
        }
        this.category = Objects.requireNonNull(category, "Product category cannot be null.");
        this.rating = rating;
    }

    public Product(Long id, String title, Double price, String description, Category category, String image, Rating rating) {
        this.id = id;
        this.title = Objects.requireNonNull(title, "Product title cannot be null.");
        if (title.isBlank()) {
            throw new IllegalArgumentException("Product title cannot be blank.");
        }
        if (title.length() > 255) {
            throw new IllegalArgumentException("Product title cannot be longer than 255 characters.");
        }
        this.price = Objects.requireNonNull(price, "Product price cannot be null.");
        if (price < 0) {
            throw new IllegalArgumentException("Product price must be zero or positive.");
        }
        this.description = Objects.requireNonNull(description, "Product description cannot be null.");
        if (description.isBlank()) {
            throw new IllegalArgumentException("Product description cannot be blank.");
        }
        this.image = Objects.requireNonNull(image, "Product image cannot be null.");
        if (image.isBlank()) {
            throw new IllegalArgumentException("Product image cannot be blank.");
        }
        this.category = Objects.requireNonNull(category, "Product category cannot be null.");
        this.rating = rating;
    }
}
