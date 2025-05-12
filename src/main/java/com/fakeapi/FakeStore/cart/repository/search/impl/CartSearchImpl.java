package com.fakeapi.FakeStore.cart.repository.search.impl;

import com.fakeapi.FakeStore.cart.domain.QCart;
import com.fakeapi.FakeStore.cart.domain.QCartItem;
import com.fakeapi.FakeStore.cart.dto.CartDTO;
import com.fakeapi.FakeStore.cart.dto.CartItemDTO;
import com.fakeapi.FakeStore.cart.repository.search.CartSearch;
import com.fakeapi.FakeStore.common.dto.PageRequestDTO;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
public class CartSearchImpl implements CartSearch {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<CartDTO> list(PageRequestDTO pageRequestDTO) {
        QCart cart = QCart.cart;
        QCartItem cartItem = QCartItem.cartItem;

        // 1. 카트만 먼저 가져옴
        List<CartDTO> carts = queryFactory
                .select(Projections.bean(CartDTO.class,
                        cart.id,
                        cart.userId,
                        cart.date
                ))
                .from(cart)
                .leftJoin(cart.products, cartItem)
                .offset(pageRequestDTO.getPageable("id").getOffset())
                .limit(pageRequestDTO.getPageable("id").getPageSize())
                .fetch();

        // 2. 가져온 카트 ID 목록
        List<Long> cartIds = carts.stream()
                .map(CartDTO::getId)
                .toList();

        // 3. 해당 카트 ID들에 대한 CartItemDTO 목록 조회
        List<CartItemDTO> cartItems = queryFactory
                .select(Projections.bean(CartItemDTO.class,
                        cartItem.cart.id.as("cartId"),
                        cartItem.productId,
                        cartItem.quantity
                ))
                .from(cartItem)
                .where(cartItem.cart.id.in(cartIds))
                .fetch();

        // 4. cartId -> List<CartItemDTO> 맵핑
        Map<Long, List<CartItemDTO>> cartItemMap = cartItems.stream()
                .collect(Collectors.groupingBy(CartItemDTO::getCartId));

        // 5. DTO에 매핑
        carts.forEach(cartDTO -> {
            List<CartItemDTO> items = cartItemMap.getOrDefault(cartDTO.getId(), new ArrayList<>());
            cartDTO.setProducts(items);
        });

        // 6. 총 개수
        long count = queryFactory
                .select(cart.count())
                .from(cart)
                .fetchOne();

        return new PageImpl<>(carts, pageRequestDTO.getPageable("id"), count);
    }
}
