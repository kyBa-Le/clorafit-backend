package com.project.api_gateway.middleware;

import com.project.api_gateway.validation.RequestValidator;
import io.jsonwebtoken.JwtException;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class JwtFilter extends AbstractGatewayFilterFactory<Object> {

    private final RequestValidator requestValidator;

    public JwtFilter(RequestValidator requestValidator) {
        super(Object.class);
        this.requestValidator = requestValidator;
    }

    @Override
    public GatewayFilter apply(Object config) {
        return (exchange, chain) -> {
            var request = exchange.getRequest();
            var response = exchange.getResponse();
            try {
                var authentication = requestValidator.validateToken(request);
                exchange.getAttributes().put("auth", authentication);

                return chain.filter(exchange);
            } catch (JwtException e) {
                response.setStatusCode(HttpStatus.UNAUTHORIZED);
                return response.setComplete();
            }
        };
    }
}
