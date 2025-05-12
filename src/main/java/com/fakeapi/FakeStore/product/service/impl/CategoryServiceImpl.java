package com.fakeapi.FakeStore.product.service.impl;

import com.fakeapi.FakeStore.product.domain.Category;
import com.fakeapi.FakeStore.product.repository.CategoryRepository;
import com.fakeapi.FakeStore.product.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> list() {
        return categoryRepository.findAll();
    }

    @Override
    public Optional<Category> read(Long id) {
        return categoryRepository.findById(id);
    }


}
