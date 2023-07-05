package com.fakeapi.FakeStore.controller;

import com.fakeapi.FakeStore.domain.Product;
import com.fakeapi.FakeStore.dto.ProductDTO;
import com.fakeapi.FakeStore.service.ProductService;
import com.fakeapi.FakeStore.service.ProductServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@Log4j2
@RequestMapping("/products")
@RequiredArgsConstructor // final or @NotNull이 붙은 필드의 생성자를 생성해주는 어노테이션
public class ProductController {

    private final ProductService productService;
    @GetMapping("/{id}")
    public Product read(@PathVariable("id") Long id){
        log.info("read id: "+ id);
        return productService.read(id);
    }

    @PostMapping
    public Product registerProduct(ProductDTO productDTO){
        return productService.register(productDTO);
    }

    @GetMapping
    public Page<Product> list(@RequestParam(required = false, defaultValue = "0") Long id, @RequestParam(required = false, defaultValue = "0") int page) {
        int size=10;
        return productService.list(page, size);
    }

}