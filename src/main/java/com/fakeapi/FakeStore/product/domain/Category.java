package com.fakeapi.FakeStore.product.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;
import java.util.Objects;

import jakarta.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CATEGORY_ID")
    private Long id;

    @Column(name = "CATEGORYNAME")
    private String name;

    public Category(String name) {
        this.name = Objects.requireNonNull(name, "Category name cannot be null.");
        if (name.isBlank()) {
            throw new IllegalArgumentException("Category name cannot be blank.");
        }
        if (name.length() > 255) {
            throw new IllegalArgumentException("Category name cannot be longer than 255 characters.");
        }
    }
}
