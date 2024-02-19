package com.ra;

import com.ra.apigateway.config.AuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class ApiGatewayApplication {
    @Autowired
    private AuthenticationFilter authenticationFilter;

    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/v1/admin/**")
                        .filters(f -> f.filter(authenticationFilter))
                        .uri("http://localhost:8082/"))
                .route(r -> r.path("/v1/admin/**", "v1/user/**")
                        .filters(f -> f.filter(authenticationFilter))
                        .uri("http://localhost:8081/"))
                .route(r -> r.path("/v1/auth/**")
                        .uri("http://localhost:8081/"))
                .route(r -> r.path("/v1/**")
                        .uri("http://localhost:8082/"))
                .build();
    }
}
