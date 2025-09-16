//package com.adrianlapos.apigw.security;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.core.Ordered;
//import org.springframework.core.annotation.Order;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.server.reactive.ServerHttpRequest;
//import org.springframework.security.core.context.ReactiveSecurityContextHolder;
//import org.springframework.security.oauth2.jwt.Jwt;
//import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
//import org.springframework.stereotype.Component;
//import org.springframework.web.server.ServerWebExchange;
//import org.springframework.web.server.WebFilter;
//import org.springframework.web.server.WebFilterChain;
//import reactor.core.publisher.Mono;
//import org.springframework.security.core.context.SecurityContext;
//
//import java.util.logging.Logger;
//@Slf4j
//@Component
//@Order(Ordered.HIGHEST_PRECEDENCE)
//public class JwtForwardingFilter implements WebFilter {
//    @Override
//    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
//        // 1. Copy cookie into Authorization header if present
//        String token = exchange.getRequest().getCookies().getFirst("access_token") != null
//                ? exchange.getRequest().getCookies().getFirst("access_token").getValue()
//                : null;
//        System.out.println("token v api gw je " + token);
//        ServerWebExchange mutatedExchange = exchange;
//
//        if (token != null) {
//            ServerHttpRequest requestWithAuth = exchange.getRequest().mutate()
//                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
//                    .build();
//            System.out.println("token added in gateway: " + "Bearer" + token);
//            mutatedExchange = exchange.mutate().request(requestWithAuth).build();
//        }
//
//        // 2. After Spring Security validates the JWT, enrich request with claims
//        ServerWebExchange finalExchange = mutatedExchange;
//        return ReactiveSecurityContextHolder.getContext()
//                .map(ctx -> ctx.getAuthentication())
//                .cast(JwtAuthenticationToken.class)
//                .flatMap(auth -> {
//                    Jwt jwt = auth.getToken();
//                    log.info("X-User-ID extracted: {}", jwt.getSubject());
//                    log.info("X-User-Email extracted: {}", jwt.getClaimAsString("email"));
//
//                    ServerHttpRequest requestWithClaims = finalExchange.getRequest().mutate()
//                            .header("X-User-Id", String.valueOf(Long.parseLong(jwt.getSubject())))
//
//                            .header("X-User-Email", jwt.getClaimAsString("email"))
//                            .build();
//
//                    return chain.filter(finalExchange.mutate().request(requestWithClaims).build());
//                })
//                .switchIfEmpty(chain.filter(finalExchange));
//    }
//}
