package com.fakeapi.FakeStore.product.controller;

import com.fakeapi.FakeStore.common.dto.PageRequestDTO;
import com.fakeapi.FakeStore.common.dto.PageResponseDTO;
import com.fakeapi.FakeStore.product.domain.Category;
import com.fakeapi.FakeStore.product.domain.Product;
import com.fakeapi.FakeStore.product.dto.ProductDTO;
import com.fakeapi.FakeStore.product.service.CategoryService;
import com.fakeapi.FakeStore.product.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@RequestMapping("/products")
@RequiredArgsConstructor // final or @NotNull이 붙은 필드의 생성자를 생성해주는 어노테이션
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;

    //Product
    @GetMapping("/{id}")
    public ProductDTO read(@PathVariable("id") Long id) {
        log.info("read id: " + id);
        return productService.read(id);
    }

    @PutMapping("/{id}")
    public ProductDTO updatePut(@PathVariable("id") Long id, @RequestBody @Valid ProductDTO productDTO) {
        return productService.update(id, productDTO);
    }

    @PatchMapping("{id}")
    public ProductDTO updatePatch(@PathVariable("id") Long id, @RequestBody @Valid ProductDTO productDTO) {
        return productService.update(id, productDTO);
    }

    //DELETE 시 CATEGORY_ID 테이블때문에 삭제 안되는 이슈
    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        productService.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public PageResponseDTO<ProductDTO> listProducts(
            @RequestParam(value = "limit", required = false) Integer limit,
            @RequestParam(value = "sort", required = false, defaultValue = "asc") String sort,
            PageRequestDTO pageRequestDTO) {

        pageRequestDTO.setSort(sort);

        if (limit != null && limit > 0) {
            return productService.listWithLimitProduct(pageRequestDTO, limit);
        }

        return productService.list(pageRequestDTO);
    }

    @PostMapping
    public Product registerProduct(@RequestBody @Valid ProductDTO productDTO) {
        return productService.register(productDTO);
    }

    // Category
    @GetMapping("/categories")
    public List<Category> categoryList() {
        return categoryService.list();
    }

    @GetMapping("/category/{id}")
    public Optional<Category> readId_category(@PathVariable("id") Long id) {
        return categoryService.read(id);
    }


    @GetMapping("/category/{categoryName}")
    public List<ProductDTO> readName_category(@PathVariable("categoryName") String categoryName) {
        return productService.listByCategoryName(categoryName);
    }
}