package com.fakeapi.FakeStore.domain;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@AllArgsConstructor
@Getter
@Builder
@NoArgsConstructor
@ToString
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double price;
    private String title;
//    private String description;

//    private String category;

//    private ImageSet image;
//  rating -> json 객체인데 어떻게 표현해야할지 모르겠음...{}
}
