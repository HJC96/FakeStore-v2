package com.fakeapi.FakeStore.domain;


import jakarta.annotation.Nullable;
import lombok.*;

import jakarta.persistence.*;

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

//    @ManyToOne(cascade = CascadeType.ALL) 삭제시에도 영향을 미침
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "CATEGORY_ID", nullable=false)
    private Category category;
//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(
//            name = "PRODUCT_CATEGORY",
//            joinColumns = @JoinColumn(name = "PRODUCT_ID"),
//            inverseJoinColumns = @JoinColumn(name = "CATEGORY_ID"))
//    private List<Category> categories = new ArrayList<>();

    @Column(name = "IMAGE")
    private String image;

    @Embedded
    @Column(name = "RATING", nullable = true) // Rating이 없는 Product가 들어올수 있다.
    private Rating rating;

}
