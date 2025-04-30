package com.fakeapi.FakeStore.cart.repository;

import com.fakeapi.FakeStore.cart.domain.Cart;
import com.fakeapi.FakeStore.cart.repository.search.CartSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> , CartSearch {
//    List<Cart> findAllByCartName(String name);
    Page<Cart> findAll(Pageable pageable);
    Page<Cart> findAllByDateBetween(LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);

    List<Cart> findAllByUserId(Long userId);




}
