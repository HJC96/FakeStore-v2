package com.fakeapi.FakeStore.product.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ProductTest {

    @Test
    void 상품_제목이_비어있으면_예외가_발생한다() {
        Product product = new Product();
        product.setTitle("");
        product.setPrice(10.0);
        product.setDescription("description");
        product.setImage("image.jpg");
        Category category = new Category();
        category.setName("category");
        product.setCategory(category);

        assertThatThrownBy(product::validate)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Product title cannot be blank.");
    }

    @Test
    void 상품_가격이_null이면_예외가_발생한다() {
        Product product = new Product();
        product.setTitle("title");
        product.setPrice(null);
        product.setDescription("description");
        product.setImage("image.jpg");
        Category category = new Category();
        category.setName("category");
        product.setCategory(category);

        assertThatThrownBy(product::validate)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Product price cannot be null.");
    }

    @Test
    void 상품_가격이_음수이면_예외가_발생한다() {
        Product product = new Product();
        product.setTitle("title");
        product.setPrice(-10.0);
        product.setDescription("description");
        product.setImage("image.jpg");
        Category category = new Category();
        category.setName("category");
        product.setCategory(category);

        assertThatThrownBy(product::validate)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Product price must be zero or positive.");
    }

    @Test
    void 상품_설명이_비어있으면_예외가_발생한다() {
        Product product = new Product();
        product.setTitle("title");
        product.setPrice(10.0);
        product.setDescription("");
        product.setImage("image.jpg");
        Category category = new Category();
        category.setName("category");
        product.setCategory(category);

        assertThatThrownBy(product::validate)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Product description cannot be blank.");
    }

    @Test
    void 상품_이미지가_비어있으면_예외가_발생한다() {
        Product product = new Product();
        product.setTitle("title");
        product.setPrice(10.0);
        product.setDescription("description");
        product.setImage("");
        Category category = new Category();
        category.setName("category");
        product.setCategory(category);

        assertThatThrownBy(product::validate)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Product image cannot be blank.");
    }

    @Test
    void 상품_카테고리가_null이면_예외가_발생한다() {
        Product product = new Product();
        product.setTitle("title");
        product.setPrice(10.0);
        product.setDescription("description");
        product.setImage("image.jpg");
        product.setCategory(null);

        assertThatThrownBy(product::validate)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Product category cannot be null.");
    }

    @Test
    void 상품_카테고리_이름이_비어있으면_예외가_발생한다() {
        Product product = new Product();
        product.setTitle("title");
        product.setPrice(10.0);
        product.setDescription("description");
        product.setImage("image.jpg");
        Category category = new Category();
        category.setName("");
        product.setCategory(category);

        assertThatThrownBy(product::validate)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Category name cannot be blank.");
    }

    @Test
    void 상품_평점_비율이_null이면_예외가_발생한다() {
        Product product = new Product();
        product.setTitle("title");
        product.setPrice(10.0);
        product.setDescription("description");
        product.setImage("image.jpg");
        Category category = new Category();
        category.setName("category");
        product.setCategory(category);
        Rating rating = new Rating();
        rating.setRate(null);
        rating.setCount(10);
        product.setRating(rating);

        assertThatThrownBy(product::validate)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Rating rate cannot be null.");
    }

    @Test
    void 상품_평점_비율이_음수이면_예외가_발생한다() {
        Product product = new Product();
        product.setTitle("title");
        product.setPrice(10.0);
        product.setDescription("description");
        product.setImage("image.jpg");
        Category category = new Category();
        category.setName("category");
        product.setCategory(category);
        Rating rating = new Rating();
        rating.setRate(-1.0);
        rating.setCount(10);
        product.setRating(rating);

        assertThatThrownBy(product::validate)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Rating rate must be zero or positive.");
    }

    @Test
    void 상품_평점_개수가_null이면_예외가_발생한다() {
        Product product = new Product();
        product.setTitle("title");
        product.setPrice(10.0);
        product.setDescription("description");
        product.setImage("image.jpg");
        Category category = new Category();
        category.setName("category");
        product.setCategory(category);
        Rating rating = new Rating();
        rating.setRate(4.5);
        rating.setCount(null);
        product.setRating(rating);

        assertThatThrownBy(product::validate)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Rating count cannot be null.");
    }

    @Test
    void 상품_평점_개수가_음수이면_예외가_발생한다() {
        Product product = new Product();
        product.setTitle("title");
        product.setPrice(10.0);
        product.setDescription("description");
        product.setImage("image.jpg");
        Category category = new Category();
        category.setName("category");
        product.setCategory(category);
        Rating rating = new Rating();
        rating.setRate(4.5);
        rating.setCount(-10);
        product.setRating(rating);

        assertThatThrownBy(product::validate)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Rating count must be zero or positive.");
    }
}
