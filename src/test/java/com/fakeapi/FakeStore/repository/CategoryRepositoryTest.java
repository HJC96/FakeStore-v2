package com.fakeapi.FakeStore.repository;

import com.fakeapi.FakeStore.product.domain.Category;
import com.fakeapi.FakeStore.product.repository.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    private Category category;

    @BeforeEach
    public void setUp() {
        category = new Category();
        category.setName("men's clothing");
    }

    @Test
    public void testSaveCategory() {
        Category savedCategory = categoryRepository.save(category);
        assertNotNull(savedCategory.getId());
    }

}
