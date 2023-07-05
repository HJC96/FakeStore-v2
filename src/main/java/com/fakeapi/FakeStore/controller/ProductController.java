package com.fakeapi.FakeStore.controller;

import com.fakeapi.FakeStore.dto.ProductDTO;
import com.fakeapi.FakeStore.service.ProductService;
import com.fakeapi.FakeStore.service.ProductServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
@RequestMapping("/products")
@RequiredArgsConstructor // final or @NotNull이 붙은 필드의 생성자를 생성해주는 어노테이션
public class ProductController {

    private final ProductService productService;
    @GetMapping("/{id}")
    public ProductDTO read(@PathVariable("id") Long id){
        log.info("read id: "+ id);
        return productService.read(id);
    }

}