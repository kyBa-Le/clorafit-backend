package com.project.auth.presentation.security.jwt_utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtProvider {
    @Value("${spring.jwt.secret-key}")
    private String SECRET;
    @Value("${spring.jwt.expiration-time}")
    private long expiration;

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(String phone) {
        var expirationDate = new Date((new Date()).getTime() + expiration);
        return Jwts.builder()
                .setIssuedAt(new Date())
                .setExpiration(expirationDate)
                .setSubject(phone)
                .signWith(this.getSigningKey())
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Claims claims = this.extractClaims(token);
            return claims.getExpiration().after(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    public String getPhoneFromToken(String token) {
        Claims claims = this.extractClaims(token);
        return claims.getSubject();
    }

    public Claims extractClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(this.getSigningKey())
                .build()
                .parseClaimsJws(token).getBody();
    }

}
