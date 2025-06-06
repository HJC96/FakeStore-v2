package com.fakeapi.FakeStore.common.util;
import java.util.Collection;
import java.util.Iterator;

import com.fakeapi.FakeStore.member.dto.LoginInfoDTO;
import com.fakeapi.FakeStore.member.dto.LoginUserDTO;
import com.fakeapi.FakeStore.common.security.jwt.token.JwtAuthenticationToken;
import org.springframework.core.MethodParameter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;


public class IfLoginArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterAnnotation(IfLogin.class) != null
                && parameter.getParameterType() == LoginUserDTO.class;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        Authentication authentication = null;
        try {
            authentication = SecurityContextHolder.getContext().getAuthentication();
        } catch (Exception ex) {
            return null;
        }
        if (authentication == null) {
            return null;
        }

        JwtAuthenticationToken jwtAuthenticationToken = (JwtAuthenticationToken)authentication;
        LoginUserDTO loginUserDTO = new LoginUserDTO();

        Object principal = jwtAuthenticationToken.getPrincipal(); // LoginInfoDto
        if (principal == null)
            return null;

        LoginInfoDTO loginInfoDTO = (LoginInfoDTO)principal;
        loginUserDTO.setEmail(loginInfoDTO.getEmail());
        loginUserDTO.setMemberId(loginInfoDTO.getMemberId());
        loginUserDTO.setName(loginInfoDTO.getName());

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
        while (iterator.hasNext()) {
            GrantedAuthority grantedAuthority = iterator.next();
            String role = grantedAuthority.getAuthority();
//            System.out.println(role);
            loginUserDTO.addRole(role);
        }

        return loginUserDTO;
    }
}
