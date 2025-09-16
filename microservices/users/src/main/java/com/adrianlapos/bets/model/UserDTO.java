package com.adrianlapos.bets.model;

import java.util.List;

public class UserDTO {

    private Long id;
    private String username;
    private String email;
    private double balance;
    private List<Long> betslips;
    private Role role;
}
