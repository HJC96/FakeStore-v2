package com.fakeapi.FakeStore.product.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class RatingTest {

    @Test
    void 유효한_평점은_예외를_발생시키지_않는다() {
        Rating rating = new Rating(4.5, 100);
        assertThatCode(rating::validate).doesNotThrowAnyException();
    }

    @Test
    void 평점_비율이_null이면_예외가_발생한다() {
        Rating rating = new Rating(null, 100);
        assertThatThrownBy(rating::validate)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Rating rate cannot be null.");
    }

    @Test
    void 평점_비율이_음수이면_예외가_발생한다() {
        Rating rating = new Rating(-1.0, 100);
        assertThatThrownBy(rating::validate)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Rating rate must be zero or positive.");
    }

    @Test
    void 평가_개수가_null이면_예외가_발생한다() {
        Rating rating = new Rating(4.5, null);
        assertThatThrownBy(rating::validate)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Rating count cannot be null.");
    }

    @Test
    void 평가_개수가_음수이면_예외가_발생한다() {
        Rating rating = new Rating(4.5, -1);
        assertThatThrownBy(rating::validate)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Rating count must be zero or positive.");
    }
}
