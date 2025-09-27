package com.project.api_gateway.validation;

import com.project.api_gateway.util.ITokenProvider;
import com.project.api_gateway.util.JwtProvider;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Component
public class RequestValidator {
    ITokenProvider tokenProvider;

    public RequestValidator(JwtProvider jwtProvider) {
        this.tokenProvider = jwtProvider;
    }

    public String validateToken(ServerHttpRequest request) throws JwtException, IllegalArgumentException {
        Claims tokenBody = tokenProvider.extractClaims(getToken(request));
        return (tokenBody.getSubject());
    }

    public String getToken(ServerHttpRequest request) {
        String header = request.getHeaders().getFirst(AUTHORIZATION);
        if (header == null || !header.startsWith("Bearer ")) {
            return null;
        }
        return header.substring(7);
    }
}
