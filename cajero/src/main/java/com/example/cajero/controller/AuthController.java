package com.example.cajero.controller;

import com.example.cajero.dto.LoginRequest;
import com.example.cajero.dto.LoginResponse;
import com.example.cajero.service.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService service;

    public AuthController(AuthService service){
        this.service=service;
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request){
        String token=service.login(

                request.getUsername(),
                request.getPassword()
        );
        return new LoginResponse(token);
    }
}
