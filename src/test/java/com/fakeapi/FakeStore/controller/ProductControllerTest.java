package com.fakeapi.FakeStore.controller;

import com.fakeapi.FakeStore.domain.Rating;
import com.fakeapi.FakeStore.dto.ProductDTO;
import com.fakeapi.FakeStore.service.CategoryService;
import com.fakeapi.FakeStore.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class) // @WevMvcTest 어노테이션의 스캔범위: CartController 클래스 및 해당 클래스에서 사용하는 빈들
class ProductControllerTest {


    @Autowired
    private MockMvc mvc; // MockMvc를 사용하여 HTTP 요청을 테스트

    @MockBean
    private ProductService productService;

    @MockBean
    private CategoryService categoryService;

    private ProductDTO productDTO;


    @BeforeEach
    void setUp() {
        productDTO = ProductDTO.builder()
                .id(1L)
                .title("Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops")
                .price(109.95)
                .description("Your perfect pack for everyday use and walks in the forest. Stash your laptop (up to 15 inches) in the padded sleeve, your everyday")
                .category("men's clothing")
                .image("https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg")
                .rating(new Rating(3.9, 120))
                .build();
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"}) // 테스트에 사용할 사용자 정보 설정 (USER 권한을 가진 user 사용자)
    @DisplayName("GET /products/{id} 테스트")
    void testGetProduct() throws Exception {
        // Given
        given(productService.read(productDTO.getId())).willReturn(productDTO);

        // When & Then, 응답 내용 확인
        mvc.perform(get("/products/{id}", productDTO.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(productDTO.getId()))
                .andExpect(jsonPath("$.title").value(productDTO.getTitle()))
                .andExpect(jsonPath("$.price").value(productDTO.getPrice()))
                .andExpect(jsonPath("$.description").value(productDTO.getDescription()))
                .andExpect(jsonPath("$.category").value(productDTO.getCategory()))
                .andExpect(jsonPath("$.image").value(productDTO.getimage()))
                .andExpect(jsonPath("$.rating.rate").value(productDTO.getRating().getRate()))
                .andExpect(jsonPath("$.rating.count").value(productDTO.getRating().getCount()));


        verify(productService, times(1)).read(productDTO.getId()); // 서비스 메소드 호출 확인
    }


}