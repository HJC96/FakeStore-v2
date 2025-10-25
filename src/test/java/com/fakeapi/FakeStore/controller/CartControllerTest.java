package com.fakeapi.FakeStore.controller;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional // 장바구니 삭제와 조회가 충돌하지 않도록 트랜잭션 적용
@WithMockUser // 모든 테스트에 Mock 유저 적용
class CartControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private final String cartJson = "{\"id\": 1,  \"userId\": 1,  \"date\": \"2024-01-01T00:00:00.000Z\",  \"products\": []}";

    @Test
    void ID로_장바구니를_조회한다() throws Exception {
        mockMvc.perform(get("/carts/1"))
                .andExpect(status().isOk());
    }

    @Test
    void PUT으로_장바구니를_수정한다() throws Exception {
        mockMvc.perform(put("/carts/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(cartJson))
                .andExpect(status().isOk());
    }

    @Test
    void 장바구니를_삭제한다() throws Exception {
        mockMvc.perform(delete("/carts/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void PATCH로_장바구니를_수정한다() throws Exception {
        mockMvc.perform(patch("/carts/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(cartJson))
                .andExpect(status().isOk());
    }

    @Test
    void 장바구니_목록을_조회한다() throws Exception {
        mockMvc.perform(get("/carts"))
                .andExpect(status().isOk());
    }

    @Test
    void 장바구니를_등록한다() throws Exception {
        mockMvc.perform(post("/carts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(cartJson))
                .andExpect(status().isCreated());
    }

    @Test
    void 유저의_장바구니를_조회한다() throws Exception {
        mockMvc.perform(get("/carts/user/1"))
                .andExpect(status().isOk());
    }
}
