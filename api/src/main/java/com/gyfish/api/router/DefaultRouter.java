package com.gyfish.api.router;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author geyu1
 */
@Component
public class DefaultRouter {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {

        return builder.routes()
                .route("manager_route", r -> r.path("/manager/**")
                        .uri("http://localhost:7001"))
                .route("form_route", r -> r.path("/form/**")
                        .uri("http://localhost:7002"))
                .route("flow_route", r -> r.path("/flow/**")
                        .uri("http://localhost:7003"))
                .build();
    }

}
