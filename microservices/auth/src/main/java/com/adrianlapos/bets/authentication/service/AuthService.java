package com.adrianlapos.bets.authentication.service;


import com.adrianlapos.bets.authentication.model.UserDetails;
import com.adrianlapos.bets.authentication.repository.AuthRepository;
import com.adrianlapos.bets.authentication.JwtUtil;
import com.adrianlapos.bets.authentication.entity.User;
import com.adrianlapos.bets.authentication.model.RegisterRequest;
import com.adrianlapos.bets.authentication.model.Role;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

@Service
public class AuthService implements UserDetailsService {
    private final AuthRepository authRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthService(AuthRepository authRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil){
        this.authRepository = authRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }


    public User register(RegisterRequest registerRequest) {
        if (authRepository.findUserByUsername(registerRequest.getUsername()).isPresent()) {
            throw new RuntimeException("Username is taken");
        }

        if (authRepository.findUserByEmail(registerRequest.getEmail()).isPresent()) {
            throw new RuntimeException("Email is taken");
        }

        if (registerRequest.getPassword().length() < 8) {
            throw new RuntimeException("Password must be at least 8 characters long");
        }

        User user = new User(
                registerRequest.getUsername(),
                registerRequest.getEmail(),
                passwordEncoder.encode(registerRequest.getPassword()),
                0,
                Role.REGISTERED,
                new ArrayList<>()
        );

        return authRepository.save(user);
    }


    public String login(String credential,String password){
        Optional<User> userOpt = authRepository.findUserByCredential(credential);
        if (!userOpt.isPresent()){
            throw new RuntimeException("Invalid credentials");
        }
        User user = userOpt.get();
        if (passwordEncoder.matches(password,user.getPassword())){
            return jwtUtil.generateToken(user.getId(),user.getUsername(),user.getEmail(),user.getBalance(),new Date());
        }
        throw new RuntimeException("Invalid credentials");
    }

    public String validateTokenAndExtractUsername(String token) {
        if (jwtUtil.validateToken(token)) {
            return jwtUtil.extractUserName(token);
        }
        throw new RuntimeException("Invalid token");
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = authRepository.findUserByUsername(username);
        if (user.isEmpty()){
            throw new EntityNotFoundException();
        }
        return new UserDetails(user.get());
    }




}
