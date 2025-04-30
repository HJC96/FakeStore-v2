package com.fakeapi.FakeStore.repository;

import com.fakeapi.FakeStore.domain.Category;
import com.fakeapi.FakeStore.domain.Member;
import com.fakeapi.FakeStore.domain.Product;
import com.fakeapi.FakeStore.repository.search.ProductSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, ProductSearch {
//    Optional<Product> list();
    List<Product> findAllByCategoryName(String name);
    Page<Product> findAll(Pageable pageable);


}
