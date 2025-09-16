package com.adrianlapos.bets.service;

import com.adrianlapos.bets.entity.User;
import com.adrianlapos.bets.repo.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User findById(Long id){
        return userRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("User not found"));
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }

    public User addUser(User user){
        return userRepository.save(user);
    }

}
