package com.StudentManagmentSystem.SMS.dto.response;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddStudentResponseDto {
    private String username;
    private String rollNumber;
    private LocalDateTime createdAt;
    private LocalDate enrollmentDate;
}
