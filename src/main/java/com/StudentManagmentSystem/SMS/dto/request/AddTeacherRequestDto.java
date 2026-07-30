package com.StudentManagmentSystem.SMS.dto.request;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddTeacherRequestDto {
@NotBlank(message = "Username should not be blank")
@Pattern(        
    regexp = "^[a-zA-Z0-9_-]([a-zA-Z0-9_ -]{1,13}[a-zA-Z0-9_-])?$", 
    message = "Username must be 3-15 characters long, cannot start or end with a space, and can only contain letters, numbers, underscores, hyphens, or spaces")
private String username;


    @NotBlank(message = "Department should not be blank")
    private String department;

    @NotBlank(message = "Teacher id should not be blank")
    private String teacherId;
    
    private LocalDate hireDate;

    @NotBlank(message = "Email should not be blank")
    @Email
    private String email;
 
    private LocalDateTime createdAt;
    
    @NotBlank
    @Size(min = 5, max = 20)
    private String password;
}
