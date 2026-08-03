package com.harsh.hospital_auth_backend.controller;

import com.harsh.hospital_auth_backend.dto.LoginResponse;
import com.harsh.hospital_auth_backend.dto.RegisterRequest;
import com.harsh.hospital_auth_backend.entity.User;
import com.harsh.hospital_auth_backend.services.UserService;
import org.springframework.web.bind.annotation.*;
import com.harsh.hospital_auth_backend.dto.LoginRequest;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public User register(@RequestBody RegisterRequest request) {
        return userService.registerUser(request);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        return userService.loginUser(request);
    }

}
