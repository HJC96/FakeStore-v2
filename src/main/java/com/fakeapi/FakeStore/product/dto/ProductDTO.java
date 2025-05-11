package com.fakeapi.FakeStore.product.dto;


import com.fakeapi.FakeStore.product.domain.Rating;
import jakarta.annotation.Nullable;
import lombok.*;


@Getter
@ToString @Setter
public class ProductDTO {
    private Long id;
    private String title;
    private Double price;
    private String description;
    private String category;
    private String image;
    @Nullable
    private Rating rating;
}
