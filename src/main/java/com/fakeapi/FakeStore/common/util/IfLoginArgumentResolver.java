package com.fakeapi.FakeStore.common.util;

import com.fakeapi.FakeStore.common.security.jwt.token.JwtAuthenticationToken;
import com.fakeapi.FakeStore.member.dto.LoginInfoDTO;
import com.fakeapi.FakeStore.member.dto.LoginUserDTO;
import org.springframework.core.MethodParameter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Collection;
import java.util.Iterator;

public class IfLoginArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterAnnotation(IfLogin.class) != null
                && parameter.getParameterType() == LoginUserDTO.class;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null) {
            return null;
        }

        LoginUserDTO loginUserDTO = new LoginUserDTO();

        if (authentication instanceof JwtAuthenticationToken) {
            JwtAuthenticationToken jwtAuthenticationToken = (JwtAuthenticationToken) authentication;
            Object principal = jwtAuthenticationToken.getPrincipal();
            if (principal == null) {
                return null;
            }
            LoginInfoDTO loginInfoDTO = (LoginInfoDTO) principal;
            loginUserDTO.setEmail(loginInfoDTO.getEmail());
            loginUserDTO.setMemberId(loginInfoDTO.getMemberId());
            loginUserDTO.setName(loginInfoDTO.getName());
        } else if (authentication instanceof UsernamePasswordAuthenticationToken) {
            // @WithMockUser를 사용한 테스트 케이스 처리
            loginUserDTO.setEmail(authentication.getName());
        }

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
        while (iterator.hasNext()) {
            GrantedAuthority grantedAuthority = iterator.next();
            String role = grantedAuthority.getAuthority();
            loginUserDTO.addRole(role);
        }

        return loginUserDTO;
    }
}
