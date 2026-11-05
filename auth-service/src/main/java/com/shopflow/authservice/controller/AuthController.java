package com.shopflow.authservice.controller;

import com.shopflow.authservice.dto.RegistrationRequest;
import com.shopflow.authservice.dto.RegistrationResponse;
import com.shopflow.authservice.service.AuthService;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}