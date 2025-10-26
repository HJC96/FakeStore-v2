package com.fakeapi.FakeStore.product.service.impl;

import com.fakeapi.FakeStore.common.dto.PageRequestDTO;
import com.fakeapi.FakeStore.common.dto.PageResponseDTO;
import com.fakeapi.FakeStore.product.domain.Category;
import com.fakeapi.FakeStore.product.domain.Product;
import com.fakeapi.FakeStore.product.dto.ProductDTO;
import com.fakeapi.FakeStore.product.repository.CategoryRepository;
import com.fakeapi.FakeStore.product.repository.ProductRepository;
import com.fakeapi.FakeStore.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Override
    public Product register(ProductDTO productDTO) {
        Category category = categoryRepository.findByName(productDTO.getCategory())
                .orElseGet(() -> categoryRepository.save(new Category(productDTO.getCategory())));

        Product product = new Product(
                productDTO.getTitle(),
                productDTO.getPrice(),
                productDTO.getDescription(),
                category,
                productDTO.getImage(),
                productDTO.getRating()
        );

        return productRepository.save(product);
    }

    @Override
    public ProductDTO read(Long id) {
        Product product = productRepository.findById(id).orElseThrow();
        ProductDTO productDTO = modelMapper.map(product, ProductDTO.class);
        productDTO.setCategory(product.getCategory().getName());

        return productDTO;
    }

    @Override
    public List<ProductDTO> listByCategoryName(String name) {
        List<Product> product = productRepository.findAllByCategoryName(name);

        return product.stream()
                .map(p -> {
                    ProductDTO productDTO = modelMapper.map(p, ProductDTO.class);
                    productDTO.setCategory(p.getCategory().getName());
                    return productDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public PageResponseDTO<ProductDTO> list(PageRequestDTO pageRequestDTO) {
        Page<ProductDTO> result = productRepository.list(pageRequestDTO);

        return PageResponseDTO.<ProductDTO>builder()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(result.toList())
                .total((int) result.getTotalElements())
                .build();
    }

    @Override
    public PageResponseDTO<ProductDTO> listWithLimitProduct(PageRequestDTO pageRequestDTO, int limit) {
        Pageable pageableWithLimit = pageRequestDTO.getPageableWithLimit(limit);
        Page<Product> productPage = productRepository.findAll(pageableWithLimit);
        List<ProductDTO> dtoList = productPage.getContent().stream()
                .map(p -> modelMapper.map(p, ProductDTO.class))
                .collect(Collectors.toList());

        return PageResponseDTO.<ProductDTO>builder()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total((int) productPage.getTotalElements())
                .build();
    }

    @Override
    public ProductDTO update(Long id, ProductDTO productDTO) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Category category = categoryRepository.findByName(productDTO.getCategory())
                .orElseGet(() -> categoryRepository.save(new Category(productDTO.getCategory())));

        Product updatedProduct = new Product(
                product.getId(),
                productDTO.getTitle(),
                productDTO.getPrice(),
                productDTO.getDescription(),
                category,
                productDTO.getImage(),
                productDTO.getRating()
        );

        productRepository.save(updatedProduct);

        ProductDTO updatedProductDTO = modelMapper.map(updatedProduct, ProductDTO.class);
        updatedProductDTO.setCategory(category.getName());
        return updatedProductDTO;
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }
}
