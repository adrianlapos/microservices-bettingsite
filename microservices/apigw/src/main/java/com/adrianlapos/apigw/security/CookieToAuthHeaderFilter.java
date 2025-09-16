package com.adrianlapos.apigw.security;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)  // Runs before Spring Security
public class CookieToAuthHeaderFilter implements WebFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        String token = exchange.getRequest().getCookies().getFirst("access_token") != null
                ? exchange.getRequest().getCookies().getFirst("access_token").getValue()
                : null;

        if (token != null) {
            ServerHttpRequest requestWithAuth = exchange.getRequest().mutate()
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                    .build();

            exchange = exchange.mutate().request(requestWithAuth).build();
        }

        return chain.filter(exchange);
    }
}

