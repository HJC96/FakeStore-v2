package com.fakeapi.FakeStore.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@WithMockUser(roles = "ADMIN")
class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void 회원가입을_한다() throws Exception {
        String signupJson = "{\"email\": \"test@example.com\",  \"password\": \"password123\",  \"name\": \"Test Name\",  \"birthYear\": \"1990\",  \"birthMonth\": \"1\",  \"birthDay\": \"1\",  \"gender\": \"MALE\"}";

        mockMvc.perform(post("/members/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(signupJson))
                .andExpect(status().isCreated());
    }

    @Test
    void 로그인을_한다() throws Exception {
        String loginJson = "{\"email\": \"john@gmail.com\", \"password\": \"0000\"}";

        mockMvc.perform(post("/members/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(loginJson))
                .andExpect(status().isOk());
    }
}
