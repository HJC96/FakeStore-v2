package com.fakeapi.FakeStore.controller;

import com.fakeapi.FakeStore.domain.Product;
import com.fakeapi.FakeStore.dto.PageRequestDTO;
import com.fakeapi.FakeStore.dto.PageResponseDTO;
import com.fakeapi.FakeStore.dto.ProductDTO;
import com.fakeapi.FakeStore.service.ProductService;
import com.fakeapi.FakeStore.service.ProductServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

//    @GetMapping
//    public Page<Product> read_limit(@RequestParam(value="limit",required = false, defaultValue = "0") int limit, @RequestParam(required = false, defaultValue = "0") int page){
//        int size=10;
//        if(limit != 0)
//            return productService.list(page, limit);
//        else
//            return productService.list(page, size);
//    }

//    @PostMapping
//    public Product registerProduct(ProductDTO productDTO){
//        return productService.register(productDTO);
//    }

//    @GetMapping
//    public PageResponseDTO<ProductDTO> list(PageRequestDTO pageRequestDTO) {
//        return productService.list(pageRequestDTO);
//    }

    @GetMapping
    public List<ProductDTO> lists() {
        return productService.list();
    }


}