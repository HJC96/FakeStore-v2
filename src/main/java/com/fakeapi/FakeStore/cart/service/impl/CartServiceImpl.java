package com.fakeapi.FakeStore.cart.service.impl;

import com.fakeapi.FakeStore.cart.domain.Cart;
import com.fakeapi.FakeStore.cart.domain.CartItem;
import com.fakeapi.FakeStore.cart.dto.CartDTO;
import com.fakeapi.FakeStore.cart.dto.CartItemDTO;
import com.fakeapi.FakeStore.cart.repository.CartItemRepository;
import com.fakeapi.FakeStore.cart.repository.CartRepository;
import com.fakeapi.FakeStore.cart.service.CartService;
import com.fakeapi.FakeStore.common.dto.PageRequestDTO;
import com.fakeapi.FakeStore.common.dto.PageResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ModelMapper modelMapper;

    @Override
    public CartDTO read(Long id) {
        Cart cart = cartRepository.findById(id).orElseThrow();
        return modelMapper.map(cart, CartDTO.class);
    }

    @Override
    public Cart register(CartDTO cartDTO) {
        List<CartItem> cartItems = new ArrayList<>();
        Cart cart = new Cart(cartDTO.getUserId(), cartDTO.getDate(), cartItems);
        List<CartItem> cartItemList = cartDTO.getProducts().stream()
                .map(cartItemDTO -> new CartItem(cart, cartItemDTO.getProductId(), cartItemDTO.getQuantity()))
                .collect(Collectors.toList());
        cart.getProducts().addAll(cartItemList);
        return cartRepository.save(cart);
    }

    @Override
    public CartDTO update(Long id, CartDTO cartDTO) {
        Cart cart = cartRepository.findById(id).orElseThrow(() -> new RuntimeException("Cart not found"));

        cartItemRepository.deleteAll(cart.getProducts());
        cart.getProducts().clear();

        List<CartItem> cartItemList = cartDTO.getProducts().stream()
                .map(cartItemDTO -> new CartItem(cart, cartItemDTO.getProductId(), cartItemDTO.getQuantity()))
                .collect(Collectors.toList());

        cart.getProducts().addAll(cartItemList);

        Cart updatedCart = cartRepository.save(cart);

        return modelMapper.map(updatedCart, CartDTO.class);
    }

    @Override
    public PageResponseDTO<CartDTO> list(PageRequestDTO pageRequestDTO) {
        Page<CartDTO> result = cartRepository.list(pageRequestDTO);

        return PageResponseDTO.<CartDTO>builder()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(result.toList())
                .total((int) result.getTotalElements())
                .build();
    }

    @Override
    public PageResponseDTO<CartDTO> listWithLimitCart(PageRequestDTO pageRequestDTO, int limit) {
        Pageable pageableWithLimit = pageRequestDTO.getPageableWithLimit(limit);
        Page<Cart> productPage = cartRepository.findAll(pageableWithLimit);
        List<CartDTO> dtoList = productPage.getContent().stream()
                .map(cart -> modelMapper.map(cart, CartDTO.class))
                .collect(Collectors.toList());

        return PageResponseDTO.<CartDTO>builder()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total((int) productPage.getTotalElements())
                .build();
    }

    @Override
    public void delete(Long id) {
        cartRepository.deleteById(id);
    }

    @Override
    public PageResponseDTO<CartDTO> listWithDateRange(PageRequestDTO pageRequestDTO, LocalDateTime startDate, LocalDateTime endDate) {
        Pageable pageable = pageRequestDTO.getPageable();
        Page<Cart> cartPage = cartRepository.findAllByDateBetween(startDate, endDate, pageable);

        List<CartDTO> cartDTOList = cartPage.getContent().stream()
                .map(cart -> modelMapper.map(cart, CartDTO.class))
                .collect(Collectors.toList());

        return PageResponseDTO.<CartDTO>builder()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(cartDTOList)
                .total((int) cartPage.getTotalElements())
                .build();
    }

    @Override
    public List<CartDTO> readUserCart(Long id) {
        List<Cart> cartList = cartRepository.findAllByUserId(id);
        return cartList.stream()
                .map(cart -> modelMapper.map(cart, CartDTO.class))
                .collect(Collectors.toList());
    }
}

