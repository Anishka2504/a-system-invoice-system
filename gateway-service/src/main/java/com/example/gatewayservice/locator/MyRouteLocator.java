package com.example.gatewayservice.locator;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

public class MyRouteLocator {

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(p -> p
                        .path("/greeting")
                        .uri("http://localhost:1111")
                )
                .route(p -> p
                        .path("/find")
                        .filters(f -> f.circuitBreaker(config ->
                                config.setFallbackUri("forward:/fallback")))
                        .uri("http:/localhost:1111"))
                .build();
    }


}
