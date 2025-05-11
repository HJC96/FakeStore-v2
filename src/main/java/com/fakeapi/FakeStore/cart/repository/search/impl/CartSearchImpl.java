package com.fakeapi.FakeStore.cart.repository.search.impl;

import com.fakeapi.FakeStore.cart.domain.QCart;
import com.fakeapi.FakeStore.cart.domain.QCartItem;
import com.fakeapi.FakeStore.cart.dto.CartDTO;
import com.fakeapi.FakeStore.cart.repository.search.CartSearch;
import com.fakeapi.FakeStore.common.dto.PageRequestDTO;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

@Log4j2
@RequiredArgsConstructor
public class CartSearchImpl implements CartSearch {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<CartDTO> list(PageRequestDTO pageRequestDTO) {
        QCart cart = QCart.cart;
        QCartItem cartItem = QCartItem.cartItem;

        // 페이징 적용
        JPAQuery<CartDTO> cartQuery = queryFactory
                .select(Projections.bean(CartDTO.class,
                        cart.id,
                        cart.userId,
                        cart.date
                ))
                .from(cart)
                .leftJoin(cart.products, cartItem)
                .offset(pageRequestDTO.getPageable("id").getOffset())
                .limit(pageRequestDTO.getPageable("id").getPageSize());

        List<CartDTO> list = cartQuery.fetch();

        long count = cartQuery.fetchCount();

        return new PageImpl<>(list, pageRequestDTO.getPageable("id"), count);
    }
}
