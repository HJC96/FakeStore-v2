package com.fakeapi.FakeStore.controller;

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
@WithMockUser(roles = "ADMIN")
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private final String productJson = "{\"id\": 1,  \"title\": \"Test Title\",  \"price\": 99.99,  \"description\": \"Test description\",  \"category\": \"electronics\",  \"image\": \"test.jpg\", \"rating\": {\"rate\": 4.5, \"count\": 100}}";

    @Test
    void ID로_상품을_조회한다() throws Exception {
        mockMvc.perform(get("/products/1"))
                .andExpect(status().isOk());
    }

    @Test
    void PUT으로_상품을_수정한다() throws Exception {
        mockMvc.perform(put("/products/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(productJson))
                .andExpect(status().isOk());
    }

    @Test
    void 상품을_삭제한다() throws Exception {
        mockMvc.perform(delete("/products/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void PATCH로_상품을_수정한다() throws Exception {
        mockMvc.perform(patch("/products/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(productJson))
                .andExpect(status().isOk());
    }

    @Test
    void 상품_목록을_조회한다() throws Exception {
        mockMvc.perform(get("/products"))
                .andExpect(status().isOk());
    }

    @Test
    void 상품을_등록한다() throws Exception {
        mockMvc.perform(post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(productJson))
                .andExpect(status().isOk());
    }

    @Test
    void 카테고리_목록을_조회한다() throws Exception {
        mockMvc.perform(get("/products/categories"))
                .andExpect(status().isOk());
    }
}
