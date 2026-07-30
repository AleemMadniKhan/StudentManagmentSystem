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
public class AddStudentRequestDto {

    @NotBlank(message = "Username should not be null")
    @Pattern(        
        regexp = "^[a-zA-Z0-9_-]{3,15}$", 
        message = "Username must be 3-15 characters long and contain "+
        "only letters, numbers, underscores, or hyphens")
    private String username;

    @NotBlank(message = "Roll number should not be blank")
    private String rollNumber;

    private LocalDate enrollmentDate;
    
    @NotBlank(message = "Email should not be blank")
    @Email
    private String email;
    
    private LocalDateTime createdAt;
    
    @NotBlank
    @Size(min = 5, max = 20)
    private String password;
}
