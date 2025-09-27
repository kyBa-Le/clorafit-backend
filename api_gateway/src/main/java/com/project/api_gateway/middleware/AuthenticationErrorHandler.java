package com.project.api_gateway.middleware;

import io.jsonwebtoken.JwtException;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.AbstractErrorWebExceptionHandler;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import java.time.Instant;
import java.util.Date;
import java.util.Map;

@Component
@Order(-2)
public class AuthenticationErrorHandler extends AbstractErrorWebExceptionHandler {
    public AuthenticationErrorHandler(
            ErrorAttributes errorAttributes,
            ApplicationContext applicationContext,
            ServerCodecConfigurer serverCodecConfigurer) {
        super(errorAttributes, new WebProperties.Resources(), applicationContext);
        this.setMessageWriters(serverCodecConfigurer.getWriters());
        this.setMessageReaders(serverCodecConfigurer.getReaders());
    }

    @Override
    protected RouterFunction<ServerResponse> getRoutingFunction(ErrorAttributes errorAttributes) {
        return RouterFunctions.route(RequestPredicates.all(), request -> {
            Throwable error = getError(request);
            if (error instanceof JwtException || error instanceof IllegalArgumentException) {
                return ServerResponse.status(HttpStatus.UNAUTHORIZED)
                        .bodyValue(Map.of("timestamp", Date.from(Instant.now()), "status", "401","error", "Unauthorized", "message", "Authentication token is invalid"));
            }
            return ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        });
    }
}
