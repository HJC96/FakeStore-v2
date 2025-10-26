package com.fakeapi.FakeStore.cart.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CartItemTest {

    private Cart validCart;

    @BeforeEach
    void setUp() {
        validCart = new Cart(1L, LocalDateTime.now(), new ArrayList<>());
    }

    @Test
    void 유효한_장바구니_아이템은_예외를_발생시키지_않는다() {
        assertThatCode(() -> new CartItem(validCart, 1L, 1)).doesNotThrowAnyException();
    }

    @Test
    void 장바구니가_null이면_예외가_발생한다() {
        assertThatThrownBy(() -> new CartItem(null, 1L, 1))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("Cart cannot be null.");
    }

    @Test
    void 상품_ID가_null이면_예외가_발생한다() {
        assertThatThrownBy(() -> new CartItem(validCart, null, 1))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("Product ID cannot be null.");
    }

    @Test
    void 수량이_0이하이면_예외가_발생한다() {
        assertThatThrownBy(() -> new CartItem(validCart, 1L, 0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Quantity must be positive.");

        assertThatThrownBy(() -> new CartItem(validCart, 1L, -1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Quantity must be positive.");
    }
}
