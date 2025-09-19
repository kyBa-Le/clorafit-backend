package com.project.api_gateway.validation;

import com.project.api_gateway.util.ITokenProvider;
import com.project.api_gateway.util.JwtProvider;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class RequestValidator {
    ITokenProvider tokenProvider;

    public RequestValidator(JwtProvider jwtProvider) {
        this.tokenProvider = jwtProvider;
    }

    public Authentication validateToken(HttpServletRequest request) {
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (header == null || !header.startsWith("Bearer ")) {
            throw new BadCredentialsException("Missing or Invalid Authorization header");
        }
        String token = header.substring(7);

        try {
            Claims tokenBody = tokenProvider.extractClaims(token);
            return new UsernamePasswordAuthenticationToken(tokenBody.getSubject(), null, List.of());
        } catch (JwtException e) {
            throw new BadCredentialsException("Invalid token");
        }
    }
}
