package com.harsh.hospital_auth_backend.services;

import com.harsh.hospital_auth_backend.dto.LoginResponse;
import com.harsh.hospital_auth_backend.dto.RegisterRequest;
import com.harsh.hospital_auth_backend.repository.UserRepository;
import com.harsh.hospital_auth_backend.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.harsh.hospital_auth_backend.dto.LoginRequest;
import com.harsh.hospital_auth_backend.security.JwtService;

@Service
public class UserService {
    private final UserRepository userRepository;
    public final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public User registerUser(RegisterRequest request) {

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        User user = new User();

        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());

        return userRepository.save(user);
    }

    public LoginResponse loginUser(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid Email"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid Password");
        }

        String token = jwtService.generateToken(user.getEmail());

        return new LoginResponse(token, user.getRole());
    }

}
