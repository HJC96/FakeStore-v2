package com.fakeapi.FakeStore.product.repository;

import com.fakeapi.FakeStore.product.domain.Product;
import com.fakeapi.FakeStore.common.token.repository.search.ProductSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, ProductSearch {
//    Optional<Product> list();
    List<Product> findAllByCategoryName(String name);
    Page<Product> findAll(Pageable pageable);


}
