package com.fakeapi.FakeStore.service;

import com.fakeapi.FakeStore.dto.ProductDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Log4j2
@SpringBootTest
class ProductServiceTests {
    @Autowired
    ProductService productService;
    @Test
    public void ProductServiceTest(){
        ProductDTO productDTO = productService.read(2L);
        log.info(productDTO);

    }
}