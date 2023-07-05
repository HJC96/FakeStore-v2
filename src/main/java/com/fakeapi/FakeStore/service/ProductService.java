package com.fakeapi.FakeStore.service;

import com.fakeapi.FakeStore.dto.ProductDTO;
import org.springframework.stereotype.Service;

@Service
public interface ProductService {
    ProductDTO read(Long id);
}
