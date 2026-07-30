package com.StudentManagmentSystem.SMS.mapper;

import org.springframework.stereotype.Component;

import com.StudentManagmentSystem.SMS.dto.response.LoginResponseDto;
import com.StudentManagmentSystem.SMS.model.User;

@Component
public class LoginMapper {
    public static LoginResponseDto entityToResponse(User user, String token, String refreshToken){
        LoginResponseDto response = new LoginResponseDto();
        response.setUsername(user.getUsername());
        response.setRole(user.getRole());
        response.setEmail(user.getEmail());
        response.setToken(token);
        response.setRefreshToken(refreshToken);
        return response;
    }
}
