package com.adrianlapos.apigw.security;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Component
@Order(Ordered.LOWEST_PRECEDENCE)  // Runs after Spring Security
public class JwtClaimHeaderInjector implements WebFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        return ReactiveSecurityContextHolder.getContext()
                .map(ctx -> ctx.getAuthentication())
                .cast(JwtAuthenticationToken.class)
                .flatMap(auth -> {
                    Jwt jwt = auth.getToken();

                    ServerHttpRequest requestWithClaims = exchange.getRequest().mutate()
                            .header("X-User-Id", String.valueOf(Long.parseLong(jwt.getSubject())))
                            .header("X-User-Email", jwt.getClaimAsString("email"))
                            .build();

                    ServerWebExchange newExchange = exchange.mutate().request(requestWithClaims).build();
                    return chain.filter(newExchange);
                })
                .switchIfEmpty(chain.filter(exchange));
    }
}

