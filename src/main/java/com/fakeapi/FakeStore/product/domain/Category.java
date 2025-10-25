package com.fakeapi.FakeStore.product.domain;

import lombok.*;
import org.springframework.util.StringUtils;

import jakarta.persistence.*;

@Entity
@Getter @Setter
public class Category {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CATEGORY_ID")
    private Long id;

    @Column(name = "CATEGORYNAME")
    private String name;

    public void validate() {
        if (!StringUtils.hasText(name)) {
            throw new IllegalArgumentException("Category name cannot be blank.");
        }
        if (name.length() > 255) {
            throw new IllegalArgumentException("Category name cannot be longer than 255 characters.");
        }
    }

//    @ManyToMany(mappedBy = "categories", cascade = CascadeType.ALL)
//    List<Product> products = new ArrayList<Product>();
}
