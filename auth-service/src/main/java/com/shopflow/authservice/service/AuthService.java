package com.shopflow.authservice.service;

import com.shopflow.authservice.dto.RegistrationRequest;
import com.shopflow.authservice.dto.RegistrationResponse;

public interface AuthService {

    RegistrationResponse registerUser(RegistrationRequest registrationRequest);
}
