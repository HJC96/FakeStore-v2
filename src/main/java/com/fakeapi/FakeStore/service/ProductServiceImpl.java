package com.fakeapi.FakeStore.service;

import com.fakeapi.FakeStore.domain.Product;
import com.fakeapi.FakeStore.dto.ProductDTO;
import com.fakeapi.FakeStore.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

//    public FakeStoreServiceImpl(ProductRepository productRepository, ModelMapper modelMapper) {
//        this.productRepository = productRepository;
//        this.modelMapper = modelMapper;
//    }

    @Override
    public ProductDTO read(Long id) {
        Optional<Product> result = productRepository.findById(id);
        Product product = result.orElseThrow();
        return modelMapper.map(product, ProductDTO.class);
    }
}
