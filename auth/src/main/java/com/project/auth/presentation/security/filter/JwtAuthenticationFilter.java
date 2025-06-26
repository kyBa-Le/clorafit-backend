package com.project.auth.presentation.security.filter;

import com.project.auth.infrastructure.util.JwtProvider;
import com.project.auth.presentation.security.service.JwtAuthenticationService;
import io.micrometer.common.lang.NonNull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtProvider jwtProvider;
    private final JwtAuthenticationService jwtAuthenticationService;

    public JwtAuthenticationFilter(JwtProvider jwtProvider, JwtAuthenticationService jwtAuthenticationService) {
        super();
        this.jwtProvider = jwtProvider;
        this.jwtAuthenticationService = jwtAuthenticationService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);

            if (jwtProvider.isTokenExpired(token)) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }

            jwtAuthenticationService.authenticate(token);
        }

        filterChain.doFilter(request, response);
    }
}
