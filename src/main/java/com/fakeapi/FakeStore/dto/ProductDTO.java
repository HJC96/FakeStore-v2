package com.fakeapi.FakeStore.dto;


import com.fakeapi.FakeStore.domain.Rating;
import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@AllArgsConstructor
@NoArgsConstructor

@Data
//@Getter
//@Setter
//@ToString
public class ProductDTO {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private Double price;
    private String description;
    private String category;
    private String imageurl;
    private Rating rating;
}
