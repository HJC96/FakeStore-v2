package com.fakeapi.FakeStore.dto;


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
//    private Long id;
    private Double price;
    private String title;
}
