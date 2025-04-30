package com.fakeapi.FakeStore.cart.service;

import com.fakeapi.FakeStore.cart.domain.Cart;
import com.fakeapi.FakeStore.cart.dto.CartDTO;
import com.fakeapi.FakeStore.common.dto.PageRequestDTO;
import com.fakeapi.FakeStore.common.dto.PageResponseDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public interface CartService {
//    List<CartDTO> list();
    PageResponseDTO<CartDTO> list(PageRequestDTO pageRequestDTO);

    PageResponseDTO<CartDTO> listWithLimitCart(PageRequestDTO pageRequestDTO, int limit);
    CartDTO read(Long id) ;

    Cart register(CartDTO cartDTO);

    CartDTO update(Long id, CartDTO cartDTO) ;

    public void delete(Long id);

    PageResponseDTO<CartDTO>  listWithDateRange(PageRequestDTO pageRequestDTO, LocalDateTime startDate, LocalDateTime endDate);

    List<CartDTO> readUserCart(Long id);
}


