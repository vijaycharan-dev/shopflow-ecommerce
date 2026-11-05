package com.shopflow.authservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthHealthController {

    @GetMapping("/health")
    public Map<String,String> healthCheck(){
        return Map.of(
                "service" , "auth-service",
                "status", "UP"
        );
    }


}
