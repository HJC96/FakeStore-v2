package com.fakeapi.FakeStore.service;

import com.fakeapi.FakeStore.domain.Product;

import com.fakeapi.FakeStore.dto.ProductDTO;
import com.fakeapi.FakeStore.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    public Product register(ProductDTO productDTO) {
        Product product = modelMapper.map(productDTO, Product.class);
        return productRepository.save(product);
    }

    @Override
    public ProductDTO read(Long id) {
        Product product = productRepository.findById(id).orElseThrow();
        ProductDTO productDTO = modelMapper.map(product,ProductDTO.class);
        productDTO.setCategory(product.getCategory().getName());

        return productDTO;
    }

    @Override
    public Page<Product> list(int page, int size) {
        return productRepository.findAll(PageRequest.of(page,size));
    }
}
