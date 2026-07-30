package com.StudentManagmentSystem.SMS.dto.response;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddTeacherResponseDto {
    private String username;
    private String department;
    private LocalDateTime createdAt;
    private LocalDate hireDate;    
    private String teacherId;
}
