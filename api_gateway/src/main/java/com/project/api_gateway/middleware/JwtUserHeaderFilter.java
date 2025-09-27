package com.project.api_gateway.middleware;

import com.project.api_gateway.util.JwtProvider;
import com.project.api_gateway.validation.RequestValidator;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class JwtUserHeaderFilter implements GlobalFilter,Ordered {
    RequestValidator requestValidator;
    JwtProvider jwtProvider;

    public JwtUserHeaderFilter(RequestValidator requestValidator, JwtProvider jwtProvider) {
        this.requestValidator = requestValidator;
        this.jwtProvider = jwtProvider;
    }

    @Override
    public int getOrder() {
        return -1;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String token = requestValidator.getToken(exchange.getRequest());
        if (token == null) {
            return chain.filter(exchange);
        }
        String userId = jwtProvider.extractClaims(token).getSubject();
        ServerHttpRequest mutatedRequest = exchange.getRequest()
                .mutate()
                .header("X-User-Id", userId)
                .build();
        ServerWebExchange mutatedExchange = exchange
                .mutate()
                .request(mutatedRequest)
                .build();

        return chain.filter(mutatedExchange);
    }
}
