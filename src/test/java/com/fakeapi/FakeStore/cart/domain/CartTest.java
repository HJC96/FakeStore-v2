package com.fakeapi.FakeStore.cart.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CartTest {

    @Test
    void 유효한_장바구니는_예외를_발생시키지_않는다() {
        assertThatCode(() -> new Cart(1L, LocalDateTime.now(), new ArrayList<>()))
                .doesNotThrowAnyException();
    }

    @Test
    void 유저_ID가_null이면_예외가_발생한다() {
        assertThatThrownBy(() -> new Cart(null, LocalDateTime.now(), new ArrayList<>()))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("User ID cannot be null.");
    }

    @Test
    void 날짜가_null이면_예외가_발생한다() {
        assertThatThrownBy(() -> new Cart(1L, null, new ArrayList<>()))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("Date cannot be null.");
    }
}
