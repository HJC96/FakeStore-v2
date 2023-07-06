package com.fakeapi.FakeStore.domain;


import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@Getter
@Builder
@NoArgsConstructor
@ToString
public class Product {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRODUCT_ID")
    private Long id;

    @Column(name = "PRICE")
    private Double price;
    @Column(name = "TITLE")
    private String title;

    @Column(name = "DSECRIPTION")
    private String description;

    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;

    @Column(name = "IMAGEURL")
    private String imageurl;

    @Embedded
    @Column(name = "RATING")
    private Rating rating;
}
