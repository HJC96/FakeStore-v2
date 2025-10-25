package com.fakeapi.FakeStore.product.domain;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.util.StringUtils;

@Entity
@AllArgsConstructor
@Getter @Setter
@Builder
@NoArgsConstructor
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

    public void validate() {
        if (!StringUtils.hasText(title)) {
            throw new IllegalArgumentException("Product title cannot be blank.");
        }
        if (title.length() > 255) {
            throw new IllegalArgumentException("Product title cannot be longer than 255 characters.");
        }
        if (price == null) {
            throw new IllegalArgumentException("Product price cannot be null.");
        }
        if (price < 0) {
            throw new IllegalArgumentException("Product price must be zero or positive.");
        }
        if (!StringUtils.hasText(description)) {
            throw new IllegalArgumentException("Product description cannot be blank.");
        }
        if (!StringUtils.hasText(image)) {
            throw new IllegalArgumentException("Product image cannot be blank.");
        }
        if (category == null) {
            throw new IllegalArgumentException("Product category cannot be null.");
        }
        category.validate();

        if (rating != null) {
            rating.validate();
        }
    }
}
