package com.ra.apigateway.config;

import com.ra.service.JwtUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class AuthenticationFilter implements GatewayFilter {
    @Autowired
    private JwtUtils jwtUtils;
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)){
            return sendError(exchange,HttpStatus.UNAUTHORIZED);
        }
        String authHerder = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
        String token = null;
        if (authHerder!= null && authHerder.startsWith("Bearer ")){
            token =  authHerder.substring(7);
        }
        try {
            jwtUtils.validateToken(token);
            String roleName= jwtUtils.getRoleNameFromToken(token);
            System.out.println(roleName);
            if(roleName.equals("ROLE_ADMIN")){
                if(exchange.getRequest().getURI().getPath().contains("/admin")){
                    return chain.filter(exchange);
                }
            } else if (roleName.equals("ROLE_USER")) {
                if(exchange.getRequest().getURI().getPath().contains("/user")){
                    return chain.filter(exchange);
                }
            }else {
                return sendError(exchange,HttpStatus.UNAUTHORIZED);
            }
        }catch (Exception exception){
            return sendError(exchange,HttpStatus.UNAUTHORIZED);
        }
        return sendError(exchange,HttpStatus.UNAUTHORIZED);
    }

    private Mono<Void> sendError(ServerWebExchange exchange, HttpStatus httpStatus){
        ServerHttpResponse serverHttpResponse = exchange.getResponse();
        serverHttpResponse.setStatusCode(httpStatus);
        return serverHttpResponse.setComplete();
    }
}
