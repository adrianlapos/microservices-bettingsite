package com.adrianlapos.apigw.authentication.controller;



import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GatewayController {

    @GetMapping("/gateway-test")
    public String test() {
        return "Gateway is working! Time: " + System.currentTimeMillis();
    }

    @GetMapping("/health")
    public String health() {
        return "Gateway health: OK";
    }
}