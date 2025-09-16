package com.adrianlapos.bets.controller;

import com.adrianlapos.bets.service.MatchService;
import com.adrianlapos.bets.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/users")
public class UserController {
   private final UserService userService;

   public UserController(UserService userService){
       this.userService = userService;
   }

   @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Long id){
       return new ResponseEntity<UserDTO>(userService.findById(id), HttpStatus.OK);
   }

}
