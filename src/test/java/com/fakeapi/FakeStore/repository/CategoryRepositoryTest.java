package com.fakeapi.FakeStore.repository;

import com.fakeapi.FakeStore.domain.Category;
import com.fakeapi.FakeStore.domain.Product;
import com.fakeapi.FakeStore.domain.Rating;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void testCategoryRepository() {


        Category category = new Category();
        category.setName("men's clothing");
//        List<Category> categoryList = new ArrayList<>();
//        categoryList.add(category);

//        product.setCategories(categoryList);

        categoryRepository.save(category);
    }
}