package com.project.api_gateway.configuration;

import com.project.api_gateway.middleware.JwtFilter;
import com.project.api_gateway.validation.RequestValidator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Value("${security.auth-paths}")
    private String[] authPaths;

    @Bean
    @Order(1)
    public SecurityFilterChain jwtFilterChain(HttpSecurity http, RequestValidator requestValidator) throws Exception {
        http.securityMatcher(authPaths)
            .csrf(AbstractHttpConfigurer::disable)
            .httpBasic(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(auth -> auth
                    .anyRequest().permitAll()
            )
            .addFilterBefore(new JwtFilter(requestValidator), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    @Order(2)
    public SecurityFilterChain openFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(AbstractHttpConfigurer::disable)
            .httpBasic(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(authorizeRequests -> {
                authorizeRequests.anyRequest().permitAll();
            });
        return http.build();
    }
}
