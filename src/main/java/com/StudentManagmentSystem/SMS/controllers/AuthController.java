package com.StudentManagmentSystem.SMS.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.StudentManagmentSystem.SMS.dto.request.LoginRequestDto;
import com.StudentManagmentSystem.SMS.dto.request.RefreshTokenRequestDto;
import com.StudentManagmentSystem.SMS.dto.response.LoginResponseDto;
import com.StudentManagmentSystem.SMS.dto.response.RefreshTokenResponseDto;
import com.StudentManagmentSystem.SMS.exceptions.UnauthorizeException;
import com.StudentManagmentSystem.SMS.service.AuthService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService service;
    public AuthController(AuthService service) {
        this.service = service;
    }

    @PostMapping("/login")
    public LoginResponseDto loginUser(@Valid @RequestBody LoginRequestDto request) throws UnauthorizeException{
        return service.varify(request);
    }

    @PostMapping("/refreshToken")
    public RefreshTokenResponseDto refreshToken(@Valid @RequestBody RefreshTokenRequestDto refreshToken){
        return service.getAccessToken(refreshToken);
    }

}