package com.StudentManagmentSystem.SMS.dto.response;

import java.time.LocalDate;

import com.StudentManagmentSystem.SMS.model.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllTeachersResponseDto {
    private Integer id;
    private String teacherId;
    private String department;
    private LocalDate hireDate;
    private String username;
    private String email;
    private Role role;   
}
