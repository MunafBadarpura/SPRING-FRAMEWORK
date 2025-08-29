package com.munaf.api_gateway.filters;

import com.munaf.api_gateway.services.JwtService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config>{

    private final JwtService jwtService;

    public AuthenticationFilter(JwtService jwtService) {
        super(Config.class);
        this.jwtService = jwtService;
    }


    @Data
    public static class Config {
        // Add fields if needed for customization
        private boolean enabled;
    }


    @Override
    public GatewayFilter apply(Config config) {
        return new GatewayFilter() {
            @Override
            public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
                if (!config.enabled) return chain.filter(exchange);

                log.info("Inside Authentication Filter For Route, {}", exchange.getRequest().getURI());

                String header = exchange.getRequest().getHeaders().getFirst("Authorization");
                if (header == null || !header.startsWith("Bearer ")) {
                    exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                    return exchange.getResponse().setComplete(); // don't go ahead
                }

                String token = header.split("Bearer ")[1];
                Long userId = jwtService.getUserIdFromToken(token);

                if (userId == null) {
                    exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                    return exchange.getResponse().setComplete(); // don't go ahead
                }

                ServerHttpRequest mutatedRequest = exchange.getRequest()
                        .mutate()
                        .header("X-User-Id", userId.toString())
                        .build();

                ServerWebExchange mutatedExchange = exchange.mutate().request(mutatedRequest).build();

                return chain.filter(mutatedExchange);

            }
        };
    }

}

