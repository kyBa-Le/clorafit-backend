package com.project.auth.infrastructure.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.auth.presentation.restfulApi.dto.base.CustomErrorResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        CustomErrorResponse errorResponse = CustomErrorResponse.build("UNAUTHORIZED", "Authentication failed or token is missing/invalid", null);
        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
    }
}
