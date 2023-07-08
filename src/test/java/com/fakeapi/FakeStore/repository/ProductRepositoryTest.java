package com.fakeapi.FakeStore.repository;

import com.fakeapi.FakeStore.domain.Category;
import com.fakeapi.FakeStore.domain.Product;
import com.fakeapi.FakeStore.domain.Rating;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Log4j2
@SpringBootTest
class ProductRepositoryTest {
    @Autowired
    private ProductRepository productRepository;
    private Rating rating;

//    @Test
//    public void testProductRepository(){
//        Category category = new Category(1L, "men's clothing");
//        Rating rating = new Rating(1L, 3.9, 120);
//        Product product = new Product(
//                1L
//                ,"Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops"
//                ,109.95
//                ,"Your perfect pack for everyday use and walks in the forest. Stash your laptop (up to 15 inches) in the padded sleeve, your everyday"
//                ,category
//                ,"https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg"
//                ,rating
//        );
//        Product result = productRepository.save(product);
//        log.info("id: "+result.getId());
//
//    }
    @Test
    public void testProductRepository(){
        Category category = new Category();

        category.setName("men's clothing");
//        List<Product> products = new ArrayList<>();
//        products.add()
//        category.setProducts(products);

        Rating rating = new Rating();
        rating.setRate(3.9);
        rating.setCount(120);

        Product product = new Product();
        product.setTitle("Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops");
        product.setPrice(109.95);
        product.setDescription("Your perfect pack for everyday use and walks in the forest. Stash your laptop (up to 15 inches) in the padded sleeve, your everyday");
//        product.setCategories(category);
        product.setImageurl("https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg");
        product.setRating(rating);
        productRepository.save(product);
        // 테스트 안되면 책 확인


    }
}