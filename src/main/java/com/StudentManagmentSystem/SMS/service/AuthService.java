package com.StudentManagmentSystem.SMS.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.StudentManagmentSystem.SMS.dto.request.LoginRequestDto;
import com.StudentManagmentSystem.SMS.dto.request.RefreshTokenRequestDto;
import com.StudentManagmentSystem.SMS.dto.response.LoginResponseDto;
import com.StudentManagmentSystem.SMS.dto.response.RefreshTokenResponseDto;
import com.StudentManagmentSystem.SMS.exceptions.UnauthorizeException;
import com.StudentManagmentSystem.SMS.mapper.LoginMapper;
import com.StudentManagmentSystem.SMS.model.RefreshToken;
import com.StudentManagmentSystem.SMS.model.User;
import com.StudentManagmentSystem.SMS.repository.UserRepo;
import com.StudentManagmentSystem.SMS.security.JwtService;

@Service
public class AuthService {
    
    private final UserRepo repo;
    private final AuthenticationManager authManager;
    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;

    public AuthService(UserRepo repo, AuthenticationManager authManager, 
    JwtService jwtService, RefreshTokenService refreshTokenService){
        this.repo = repo;
        this.authManager = authManager;
        this.jwtService = jwtService;
        this.refreshTokenService = refreshTokenService;
    
    }

    
    public LoginResponseDto varify(LoginRequestDto request) throws UnauthorizeException{
        Authentication auth = 
        authManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        if (auth.isAuthenticated()) {
            User user = repo.findByEmail(request.getEmail());
            String token = jwtService.generateToken(user);
            RefreshToken refreshToken = refreshTokenService.generatRefreshToken(user);
            return LoginMapper.entityToResponse(user, token, refreshToken.getRefreshToken());
        }
        throw new UnauthorizeException("Email or Password may wronge.");
    }

    public RefreshTokenResponseDto getAccessToken(RefreshTokenRequestDto refreshToken) {
        RefreshToken varifiedRefreshToken = refreshTokenService.varifyRefreshToken(refreshToken.getRefreshToken());
        String accesToken = jwtService.generateToken(varifiedRefreshToken.getUser());
        RefreshTokenResponseDto response = new RefreshTokenResponseDto();
        response.setAccessToken(accesToken);
        response.setRefreshToken(varifiedRefreshToken.getRefreshToken());
        return  response;
    }

}
