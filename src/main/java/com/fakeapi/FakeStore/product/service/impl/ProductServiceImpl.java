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
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Log4j2
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Override
    public Product register(ProductDTO productDTO) {
        Product product = modelMapper.map(productDTO, Product.class);
        Optional<Category> optionalCategory = categoryRepository.findByName(productDTO.getCategory());
        if (optionalCategory.isEmpty()) {
            Category category = new Category();
            category.setName(productDTO.getCategory());
            product.setCategory(category);
        } else {
            product.setCategory(optionalCategory.get());
        }

        product.setRating(productDTO.getRating());
        productRepository.save(product);
        return product;
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

        List<ProductDTO> productDTOList = new ArrayList<>();
        for (Product p : product) {
            System.out.println(p.getCategory());
            ProductDTO productDTO = modelMapper.map(p, ProductDTO.class);
            productDTO.setCategory(p.getCategory().getName());
            productDTOList.add(productDTO);
        }
        return productDTOList;
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
        List<ProductDTO> dtoList = new ArrayList<>();
        for (Product p : productPage.getContent()) {
            ProductDTO productDTO = modelMapper.map(p, ProductDTO.class);
            dtoList.add(productDTO);
        }

        Page<ProductDTO> result = new PageImpl<>(dtoList, pageableWithLimit, productPage.getTotalElements());

        return PageResponseDTO.<ProductDTO>builder()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(result.toList())
                .total((int) result.getTotalElements())
                .build();
    }

    @Override
    public ProductDTO update(Long id, ProductDTO productDTO) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        Optional<Category> optionalCategory = categoryRepository.findByName(productDTO.getCategory());

        if (optionalProduct.isPresent()) {
            // modelMapper가 예상대로 동작하지 않음. 수동 매핑
            // modelMapper.map(productDTO, Product.class);
            Product product = optionalProduct.get();
            product.setTitle(productDTO.getTitle());
            product.setPrice(productDTO.getPrice());
            product.setDescription(productDTO.getDescription());
            product.setImage(productDTO.getImage());
            if (optionalCategory.isPresent())
                product.setCategory(optionalCategory.get());
            else {
                Category category = new Category();
                category.setName(productDTO.getCategory());
                categoryRepository.save(category);
                product.setCategory(category);
            }
            product.setRating(productDTO.getRating());
            productRepository.save(product);

            ProductDTO updatedProductDTO = modelMapper.map(product, ProductDTO.class);
            updatedProductDTO.setCategory(optionalCategory.get().getName());
            return updatedProductDTO;
        } else {
            throw new RuntimeException("Product not found");
        }
    }

    @Override
    public void delete(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);

        if (!optionalProduct.isPresent()) {
            throw new RuntimeException("Product not found");
        }
        productRepository.deleteById(id);
    }
}
