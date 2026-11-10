package com.shopflow.authservice.service;

import com.shopflow.authservice.dto.LoginRequest;
import com.shopflow.authservice.dto.LoginResponse;
import com.shopflow.authservice.dto.RegistrationRequest;
import com.shopflow.authservice.dto.RegistrationResponse;

public interface AuthService {

    RegistrationResponse registerUser(RegistrationRequest registrationRequest);
    LoginResponse login(LoginRequest loginRequest);
}
