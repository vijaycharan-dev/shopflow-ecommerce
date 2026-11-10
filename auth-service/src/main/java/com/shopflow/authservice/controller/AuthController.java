package com.shopflow.authservice.controller;

import com.shopflow.authservice.dto.LoginRequest;
import com.shopflow.authservice.dto.LoginResponse;
import com.shopflow.authservice.dto.RegistrationRequest;
import com.shopflow.authservice.dto.RegistrationResponse;
import com.shopflow.authservice.service.AuthService;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.security.core.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<RegistrationResponse> registerUser(
            @Valid @RequestBody RegistrationRequest registrationRequest) {

        RegistrationResponse response =
                authService.registerUser(registrationRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login (@Valid @RequestBody LoginRequest loginRequest){
        LoginResponse response = authService.login(loginRequest);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/profile")
    public ResponseEntity<Map<String, Object >> profile(Authentication authentication){
        Map<String,Object> profile = new HashMap<>();
        profile.put("username",authentication.getName());
        profile.put("roles",authentication.getAuthorities());

        return ResponseEntity.ok(profile);
    }
}