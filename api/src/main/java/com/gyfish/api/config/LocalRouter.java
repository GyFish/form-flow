package com.gyfish.api.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author geyu1
 */
@Component
public class LocalRouter {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {

        return builder.routes()
                .route("manager", r -> r.path("/manager/**")
                        .uri("http://localhost:7001"))
                .route("service", r -> r.path("/service/**")
                        .uri("http://localhost:7002"))
                .build();
    }

}
