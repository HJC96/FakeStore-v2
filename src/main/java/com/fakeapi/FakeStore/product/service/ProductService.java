package com.fakeapi.FakeStore.product.service;


import com.fakeapi.FakeStore.product.domain.Product;
import com.fakeapi.FakeStore.common.dto.PageRequestDTO;
import com.fakeapi.FakeStore.common.dto.PageResponseDTO;
import com.fakeapi.FakeStore.product.dto.ProductDTO;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

import java.util.List;

@Service
@Transactional
public interface ProductService {
    Product register(ProductDTO productDTO);
    ProductDTO read(Long id);

    List<ProductDTO> listByCategoryName(String name);

    PageResponseDTO<ProductDTO> list(PageRequestDTO pageRequestDTO);

    PageResponseDTO<ProductDTO> listWithLimitProduct(PageRequestDTO pageRequestDTO, int limit);
    ProductDTO update(Long id, ProductDTO productDTO) ;
    void delete(Long id) ;

    }
