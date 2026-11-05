package com.shopflow.authservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RegistrationResponse {

    private Long id;
    private String username;
    private String email;
    private String role;
    private String message;
}
