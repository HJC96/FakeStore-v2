package com.fakeapi.FakeStore.common.config;

import com.fakeapi.FakeStore.common.security.exception.CustomAuthenticationEntryPoint;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

import static org.springframework.http.HttpMethod.OPTIONS;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final AuthenticationManagerConfig authenticationManagerConfig;
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .sessionManagement(httpSecuritySessionManagementConfigurer -> httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .formLogin(httpSecurityFormLoginConfigurer -> httpSecurityFormLoginConfigurer.disable())
                .csrf(csrf -> csrf.disable())
                .cors(httpSecurityCorsConfigurer -> httpSecurityCorsConfigurer.configurationSource(corsConfigurationSource()))
                .httpBasic(httpSecurityHttpBasicConfigurer -> httpSecurityHttpBasicConfigurer.disable())
                .authorizeHttpRequests(auth -> auth
                        // 공개 API
                        .requestMatchers("/members/login", "/members/signup", "/members/refreshToken").permitAll()
                        .requestMatchers("/swagger-ui/**", "/v3/**").permitAll()

                        // 인증 필요 API
                        .requestMatchers("/products/**", "/carts/**").authenticated()
                        .requestMatchers("/orders/**").hasRole("USER")
                        .requestMatchers("/admin/**").hasRole("ADMIN")

                        // OPTIONS preflight는 항상 허용
                        .requestMatchers(OPTIONS, "/**").permitAll()

                        // 그 외 요청은 기본적으로 인증 필요
                        .anyRequest().authenticated()
                )
                .exceptionHandling(config -> config
                        .authenticationEntryPoint(customAuthenticationEntryPoint)  // 401
                        .accessDeniedHandler(new AccessDeniedHandlerImpl())           // 403
                )
                .apply(authenticationManagerConfig);

        return httpSecurity.build();
    }

    public CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);  // true로 변경
        config.addAllowedOrigin("http://localhost:3000");  // "*" 대신 특정 origin 지정
        config.setAllowedMethods(List.of("GET", "POST", "DELETE", "PATCH", "OPTION", "PUT"));
        config.addAllowedHeader("*");
        source.registerCorsConfiguration("/**", config);

        return source;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
