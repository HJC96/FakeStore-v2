package com.fakeapi.FakeStore.controller.dto;

import java.time.LocalDateTime;
import java.util.List;

public class CartDTO {

    private Long id;
    private Long userId;
    private LocalDateTime date;
    private List<CartItemDTO> products;

    public CartDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public List<CartItemDTO> getProducts() {
        return products;
    }

    public void setProducts(List<CartItemDTO> products) {
        this.products = products;
    }

}
