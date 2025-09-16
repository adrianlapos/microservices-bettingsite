//package com.adrianlapos.apigw.authentication.service;
//
//import com.adrianlapos.apigw.authentication.entity.User;
//import com.adrianlapos.apigw.authentication.model.RegisterRequest;
//import com.adrianlapos.apigw.authentication.model.Role;
//import com.adrianlapos.apigw.authentication.model.UserDetails;
//import com.adrianlapos.apigw.authentication.repository.UserRepository;
//import com.adrianlapos.apigw.security.JwtUtil;
//import jakarta.persistence.EntityNotFoundException;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import javax.security.auth.login.CredentialException;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.Optional;
//
//@Service
//public class UserService implements UserDetailsService {
//    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;
//    private final JwtUtil jwtUtil;
//
//    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil){
//        this.userRepository = userRepository;
//        this.passwordEncoder = passwordEncoder;
//        this.jwtUtil = jwtUtil;
//    }
//
//
//    public User register(RegisterRequest registerRequest) {
//        if (userRepository.findUserByUsername(registerRequest.getUsername()).isPresent()) {
//            throw new RuntimeException("Username is taken");
//        }
//
//        if (userRepository.findUserByEmail(registerRequest.getEmail()).isPresent()) {
//            throw new RuntimeException("Email is taken");
//        }
//
//        if (registerRequest.getPassword().length() < 8) {
//            throw new RuntimeException("Password must be at least 8 characters long");
//        }
//
//        User user = new User(
//                registerRequest.getUsername(),
//                registerRequest.getEmail(),
//                passwordEncoder.encode(registerRequest.getPassword()),
//                0,
//                Role.REGISTERED,
//                new ArrayList<>()
//        );
//
//        return userRepository.save(user);
//    }
//
//
//    public String login(String credential,String password){
//        Optional<User> userOpt = userRepository.findUserByCredential(credential);
//        if (!userOpt.isPresent()){
//            throw new RuntimeException("Invalid credentials");
//        }
//        User user = userOpt.get();
//        if (passwordEncoder.matches(password,user.getPassword())){
//         return jwtUtil.generateToken(user.getUsername(),user.getEmail(),user.getBalance(),new Date());
//        }
//        throw new RuntimeException("Invalid credentials");
//    }
//
//    public String validateTokenAndExtractUsername(String token) {
//        if (jwtUtil.validateToken(token)) {
//            return jwtUtil.extractUserName(token);
//        }
//        throw new RuntimeException("Invalid token");
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Optional<User> user = userRepository.findUserByUsername(username);
//        if (user.isEmpty()){
//            throw new EntityNotFoundException();
//        }
//        return new UserDetails(user.get());
//    }
//
//
//
//
//}
