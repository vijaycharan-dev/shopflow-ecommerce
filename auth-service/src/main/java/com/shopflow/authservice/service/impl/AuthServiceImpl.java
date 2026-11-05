package com.shopflow.authservice.service.impl;

import com.shopflow.authservice.dto.RegistrationRequest;
import com.shopflow.authservice.dto.RegistrationResponse;
import com.shopflow.authservice.entity.User;
import com.shopflow.authservice.exception.DuplicateResourceException;
import com.shopflow.authservice.repository.UserRepository;
import com.shopflow.authservice.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

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
}
