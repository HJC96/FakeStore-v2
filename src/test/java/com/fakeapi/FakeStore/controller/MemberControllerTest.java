//package com.fakeapi.FakeStore.controller;
//
//import com.fakeapi.FakeStore.common.token.service.RefreshTokenService;
//import com.fakeapi.FakeStore.common.util.JwtTokenizer;
//import com.fakeapi.FakeStore.member.controller.MemberController;
//import com.fakeapi.FakeStore.member.domain.Member;
//import com.fakeapi.FakeStore.member.dto.request.MemberSignUpDTO;
//import com.fakeapi.FakeStore.member.service.MemberService;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.test.context.support.WithMockUser;
//import org.springframework.test.web.servlet.MockMvc;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.BDDMockito.given;
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@WebMvcTest(MemberController.class)
//class MemberControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private MemberService memberService;
//
//    @MockBean
//    private PasswordEncoder passwordEncoder;
//
//    @MockBean
//    private JwtTokenizer jwtTokenizer;
//
//    @MockBean
//    private RefreshTokenService refreshTokenService;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @Test
//    @DisplayName("회원가입 테스트")
//    @WithMockUser
//    void testSignup() throws Exception {
//        // Given
//        MemberSignUpDTO signUpDTO = new MemberSignUpDTO();
//        signUpDTO.setEmail("test@example.com");
//        signUpDTO.setName("Test User");
//        signUpDTO.setPassword("password123");
//
//        Member savedMember = new Member();
//        savedMember.setId(1L);
//        savedMember.setEmail(signUpDTO.getEmail());
//        savedMember.setName(signUpDTO.getName());
//
//        given(memberService.addMember(any(MemberSignUpDTO.class))).willReturn(savedMember);
//
//        // When & Then
//        mockMvc.perform(post("/members/signup")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(signUpDTO)))
//                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.memberId").value(1L))
//                .andExpect(jsonPath("$.email").value("test@example.com"))
//                .andExpect(jsonPath("$.name").value("Test User"));
//
//        verify(memberService, times(1)).addMember(any(MemberSignUpDTO.class));
//    }
//
//}