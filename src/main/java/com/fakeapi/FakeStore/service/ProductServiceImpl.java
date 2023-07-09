package com.fakeapi.FakeStore.service;

import com.fakeapi.FakeStore.domain.Product;

import com.fakeapi.FakeStore.dto.PageRequestDTO;
import com.fakeapi.FakeStore.dto.PageResponseDTO;
import com.fakeapi.FakeStore.dto.ProductDTO;
import com.fakeapi.FakeStore.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


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


//    @Override
//    public PageResponseDTO<ProductDTO> list(PageRequestDTO pageRequestDTO) {
////        Page<ProductDTO> result = productRepository.findAll(PageRequest.of(pageRequestDTO.getPage(),pageRequestDTO.getSize()));
//        Page<ProductDTO> result = productRepository.list(pageRequestDTO);
//
//        return PageResponseDTO.<ProductDTO>builder()
//                .pageRequestDTO(pageRequestDTO)
//                .dtoList(result.toList())
//                .total((int)result.getTotalElements())
//                .build();
//    }
public List<ProductDTO> list() {
    List<Product> products = productRepository.findAll();
    List<ProductDTO> dtos = products.stream()
            .map(product -> modelMapper.map(product, ProductDTO.class))
            .collect(Collectors.toList());
    return dtos;
    }
}
