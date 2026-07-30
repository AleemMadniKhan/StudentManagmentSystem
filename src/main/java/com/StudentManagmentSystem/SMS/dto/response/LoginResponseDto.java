package com.StudentManagmentSystem.SMS.dto.response;


import com.StudentManagmentSystem.SMS.model.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseDto {
    private String username;
    private String email;
    private Role role;
    private String token;
    private String refreshToken;
}
