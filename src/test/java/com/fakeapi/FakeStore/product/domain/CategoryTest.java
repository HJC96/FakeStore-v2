package com.fakeapi.FakeStore.product.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CategoryTest {

    @Test
    void 유효한_카테고리명은_예외를_발생시키지_않는다() {
        Category category = new Category();
        category.setName("electronics");
        assertThatCode(category::validate).doesNotThrowAnyException();
    }

    @Test
    void 카테고리명이_비어있으면_예외가_발생한다() {
        Category category = new Category();
        category.setName("");
        assertThatThrownBy(category::validate)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Category name cannot be blank.");
    }

    @Test
    void 카테고리명이_null이면_예외가_발생한다() {
        Category category = new Category();
        category.setName(null);
        assertThatThrownBy(category::validate)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Category name cannot be blank.");
    }

    @Test
    void 카테고리명이_255자를_초과하면_예외가_발생한다() {
        Category category = new Category();
        String longName = "a".repeat(256);
        category.setName(longName);
        assertThatThrownBy(category::validate)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Category name cannot be longer than 255 characters.");
    }
}
