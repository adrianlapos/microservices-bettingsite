package com.adrianlapos.bets.authentication.controller;


import com.adrianlapos.bets.authentication.entity.User;
import com.adrianlapos.bets.authentication.model.LoginRequest;
import com.adrianlapos.bets.authentication.model.RegisterRequest;
import com.adrianlapos.bets.authentication.model.UserDetails;
import com.adrianlapos.bets.authentication.service.AuthService;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService){
        this.authService=authService;
    }

    @GetMapping("/me")
    public Map<String, Object> me(Authentication authentication) {
        System.out.println(authentication);
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new RuntimeException("Not authenticated");
        }

        var userDetails = (UserDetails) authentication.getPrincipal();
        var claims = (Map<String, Object>) authentication.getCredentials();

        return Map.of(
                "userId", userDetails.getUsername(),  // subject / username
                "email", userDetails.getEmail(),
                "username", userDetails.getUsername()
        );
    }
    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
        String token = authService.login(loginRequest.getCredential(),loginRequest.getPassword());
        ResponseCookie responseCookie = ResponseCookie.from("access_token",token)
                .httpOnly(true)
                .secure(false)
                .path("/")
                .sameSite("Strict")
                .maxAge(12*60*60)
                .build();
        System.out.println("response cookie generated :" + responseCookie.toString());
        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, responseCookie.toString())
                .body(Map.of("message", "Login successful"));    }

    @PostMapping("register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest registerRequest) {
        try {
            User registeredUser = authService.register(registerRequest);
        } catch (Exception e) {
            throw new RuntimeException("Registration was not successful");
        }
        return new ResponseEntity<>("User registered successfully",HttpStatus.CREATED);
    }
}
