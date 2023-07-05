package com.fakeapi.FakeStore.service;


import com.fakeapi.FakeStore.domain.Product;
import com.fakeapi.FakeStore.dto.ProductDTO;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public interface ProductService {
    Product register(ProductDTO productDTO);
    Product read(Long id);
    Page<Product> list(int page, int size);

}
