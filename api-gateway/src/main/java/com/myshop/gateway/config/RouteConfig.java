package com.myshop.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouteConfig {

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {

        return builder.routes()

                // ================= USER SERVICE =================
                .route("user-service", r -> r
                        .path("/api/v1/users/**")
                        .filters(f -> f.stripPrefix(2))
                        .uri("lb://USER-SERVICE"))

                // ================= PRODUCT SERVICE =================
                .route("product-service", r -> r
                        .path("/api/v1/products/**",
                                "/api/v1/categories/**",
                                "/api/v1/reviews/**")
                        .filters(f -> f.stripPrefix(2))
                        .uri("lb://PRODUCT-SERVICE"))

                // ================= ORDER SERVICE =================
                .route("order-service", r -> r
                        .path("/api/v1/orders/**",
                                "/api/v1/carts/**")
                        .filters(f -> f.stripPrefix(2))
                        .uri("lb://ORDER-SERVICE"))

                // ================= PAYMENT SERVICE =================
                .route("payment-service", r -> r
                        .path("/api/v1/payments/**")
                        .filters(f -> f.stripPrefix(2))
                        .uri("lb://PAYMENT-SERVICE"))

                // ================= INVENTORY SERVICE =================
                .route("inventory-service", r -> r
                        .path("/api/v1/inventories/**")
                        .filters(f -> f.stripPrefix(2))
                        .uri("lb://INVENTORY-SERVICE"))

                // ================= NOTIFICATION SERVICE =================
                .route("notification-service", r -> r
                        .path("/api/v1/notifications/**")
                        .filters(f -> f.stripPrefix(2))
                        .uri("lb://NOTIFICATION-SERVICE"))

                .build();
    }
}