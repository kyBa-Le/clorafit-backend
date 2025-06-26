package com.project.auth.infrastructure.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtProvider {
    @Value("${spring.jwt.secret-key}")
    private String secret;
    @Value("${spring.jwt.expiration}")
    private long expiration;

    public SecretKey getSecretKey() {
        byte[] keyBytes = secret.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(String subject) {
        Date expirationDate = new Date((new Date().getTime()) + expiration);
        return Jwts
                .builder()
                .subject(subject)
                .expiration(expirationDate)
                .issuedAt(new Date())
                .signWith(this.getSecretKey())
                .compact();
    }

    public String getSubject(String token) {
        return this.extractClaims(token).getSubject();
    }

    public Claims extractClaims(String token) {
        return Jwts.parser()
                .verifyWith(this.getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public boolean isTokenExpired(String token) {
        Date expirationDate = this.extractClaims(token).getExpiration();
        Date now = new Date();
        return now.after(expirationDate);
    }
}
