package com.munaf.api_gateway.filters;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class InventoryLoggingFilter extends AbstractGatewayFilterFactory<InventoryLoggingFilter.Config> {

    public InventoryLoggingFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return new GatewayFilter() {
            @Override
            public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
                log.info("Pre Inventory Logging Filter, {}", exchange.getRequest().getURI());

                return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                    log.info("Post Inventory Logging Filter, {}", exchange.getResponse().getStatusCode());
                }));
            }
        };
    }

    // You can define custom config properties here (if needed)
    public static class Config {
        // Add fields if needed for customization
    }


        // WITH LAMBDA
//    @Override
//    public GatewayFilter apply(Config config) {
//        return (exchange, chain) -> {
//            log.info("Inventory Logging Filter, {}", exchange.getRequest().getURI());
//            return chain.filter(exchange);
//        };
//    }
}
