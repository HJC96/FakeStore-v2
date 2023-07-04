package com.fakeapi.FakeStore.repository;

import com.fakeapi.FakeStore.domain.Product;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@Log4j2
@SpringBootTest
class ProductRepositoryTest {
    @Autowired
    private ProductRepository productRepository;
    @Test
    public void testProductRepository(){
        Product product = new Product(
                2L,
                109.12,
                "Fjallraven"

        );
        Product result = productRepository.save(product);
        log.info("id: "+result.getId());

    }

}