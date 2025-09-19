package com.project.api_gateway.util;

import io.jsonwebtoken.Claims;

public interface ITokenProvider {
    Claims extractClaims(String token);
}
