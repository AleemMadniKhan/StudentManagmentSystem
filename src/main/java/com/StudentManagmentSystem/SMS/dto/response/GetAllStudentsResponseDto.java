package com.StudentManagmentSystem.SMS.dto.response;

import java.time.LocalDate;

import com.StudentManagmentSystem.SMS.model.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllStudentsResponseDto {

    private Integer studentId;

    private String rollNumber;

    private LocalDate enrollmentDate;
    
    private String username;

    private String email;

    private Role role;

}
