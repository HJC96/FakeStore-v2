package com.fakeapi.FakeStore.product.service;

import com.fakeapi.FakeStore.product.domain.Category;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@Transactional
public interface CategoryService {

    List<Category> list();
    Optional<Category> read(Long id) ;


}
