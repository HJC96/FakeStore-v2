package com.fakeapi.FakeStore.product.domain;

import lombok.*;

import jakarta.persistence.*;

@Entity
@Getter @Setter
public class Category {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CATEGORY_ID")
    private Long id;

    @Column(name = "CATEGORYNAME")
    private String name;

//    @ManyToMany(mappedBy = "categories", cascade = CascadeType.ALL)
//    List<Product> products = new ArrayList<Product>();
}
