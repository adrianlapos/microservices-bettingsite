//package com.adrianlapos.apigw.authentication.controller;
//
//import com.adrianlapos.apigw.authentication.entity.User;
//import com.adrianlapos.apigw.authentication.model.LoginRequest;
//import com.adrianlapos.apigw.authentication.model.RegisterRequest;
//import com.adrianlapos.apigw.authentication.service.UserService;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api/auth")
//public class UserController {
//
//    private final UserService userService;
//
//    public UserController(UserService userService){
//        this.userService=userService;
//    }
//
//    @GetMapping("login")
//    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest){
//        String token ="Bearer" + " " + userService.login(loginRequest.getCredential(),loginRequest.getPassword());
//        return new ResponseEntity<>(token, HttpStatus.OK);
//    }
//
//    @PostMapping("register")
//    public ResponseEntity<String> register(@RequestBody RegisterRequest registerRequest) {
//        try {
//            User registeredUser = userService.register(registerRequest);
//        } catch (Exception e) {
//            throw new RuntimeException("Registration was not successful");
//        }
//        return new ResponseEntity<>("User registered successfully",HttpStatus.CREATED);
//    }
//}
