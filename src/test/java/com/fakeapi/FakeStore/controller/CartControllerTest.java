package com.fakeapi.FakeStore.controller;

import com.fakeapi.FakeStore.dto.CartDTO;
import com.fakeapi.FakeStore.dto.PageRequestDTO;
import com.fakeapi.FakeStore.dto.PageResponseDTO;
import com.fakeapi.FakeStore.service.CartService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CartController.class) // @WevMvcTest 어노테이션의 스캔범위: CartController 클래스 및 해당 클래스에서 사용하는 빈들
class CartControllerTest {


    @Autowired
    private MockMvc mvc; // MockMvc를 사용하여 HTTP 요청을 테스트

    @MockBean
    private CartService cartService;


    @Test
    @WithMockUser(username = "user", roles = {"USER"}) // 테스트에 사용할 사용자 정보 설정 (USER 권한을 가진 user 사용자)
    @DisplayName("GET /carts/{id} 테스트")
    void testGetCart() throws Exception {
        // Given
        Long id = 1L;
        CartDTO cartDTO = CartDTO.builder().id(id).build();
        given(cartService.read(id)).willReturn(cartDTO);

        // When & Then
        mvc.perform(get("/carts/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        verify(cartService, times(1)).read(id); // cartService.read(id) 메소드가 1번 호출되었는지 검증, 서비스 메소드 호출 확인
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    @DisplayName("GET /carts 테스트 (페이지네이션)")
    void testListCarts() throws Exception {
        PageRequestDTO pageRequestDTO = new PageRequestDTO();
        List<CartDTO> cartList = Arrays.asList(
                CartDTO.builder().id(1L).build(),
                CartDTO.builder().id(2L).build()
        );
        PageResponseDTO<CartDTO> pageResponseDTO = PageResponseDTO.<CartDTO>builder()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(cartList)
                .total(100)
                .build();

        given(cartService.list(pageRequestDTO)).willReturn(pageResponseDTO);

        mvc.perform(get("/carts")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.page").value(1))
                .andExpect(jsonPath("$.size").value(10))
                .andExpect(jsonPath("$.total").value(100))
                .andExpect(jsonPath("$.start").value(1))
                .andExpect(jsonPath("$.end").value(10))
                .andExpect(jsonPath("$.prev").value(false))
                .andExpect(jsonPath("$.next").value(true))
                .andExpect(jsonPath("$.dtoList.length()").value(2));

        verify(cartService, times(1)).list(pageRequestDTO);
    }

}