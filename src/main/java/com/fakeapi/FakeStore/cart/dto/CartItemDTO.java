package com.fakeapi.FakeStore.cart.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor
@NoArgsConstructor
public class CartItemDTO {

    private Long productId;
    private int quantity;

}