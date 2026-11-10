package com.shopflow.authservice.service.impl;

import com.shopflow.authservice.dto.LoginRequest;
import com.shopflow.authservice.dto.LoginResponse;
import com.shopflow.authservice.dto.RegistrationRequest;
import com.shopflow.authservice.dto.RegistrationResponse;
import com.shopflow.authservice.entity.User;
import com.shopflow.authservice.exception.DuplicateResourceException;
import com.shopflow.authservice.repository.UserRepository;
import com.shopflow.authservice.security.JwtService;
import com.shopflow.authservice.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public RegistrationResponse registerUser(RegistrationRequest registrationRequest) {

        if(userRepository.existsByEmail(registrationRequest.getEmail())){
            throw new DuplicateResourceException("Email already registered");
        }

        if(userRepository.existsByUsername(registrationRequest.getUsername())){
            throw new DuplicateResourceException("Username already taken");
        }

        User user = new User();
        user.setUsername(registrationRequest.getUsername());
        user.setEmail(registrationRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
        user.setRole("CUSTOMER");

        User savedUser = userRepository.save(user);

        return new RegistrationResponse(savedUser.getId(),
                savedUser.getUsername(),
                savedUser.getEmail(),
                savedUser.getRole(),
                "User registered successfully");
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        User user = userRepository.findByEmail(loginRequest.getEmail()).orElseThrow(() -> new BadCredentialsException("Invalid email or password"));
        if(!passwordEncoder.matches(loginRequest.getPassword(),user.getPassword())){
            throw new BadCredentialsException("Invalid email or password");
        }
        String token = jwtService.generateToken(user.getId(),user.getUsername(),user.getRole());

        return new LoginResponse(token,
                "Bearer",
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getRole(),
                "Login successful");
    }
}
