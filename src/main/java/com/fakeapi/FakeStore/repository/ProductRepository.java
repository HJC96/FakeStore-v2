package com.fakeapi.FakeStore.repository;

import com.fakeapi.FakeStore.domain.Product;
import com.fakeapi.FakeStore.repository.search.ProductSearch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, ProductSearch {

}
