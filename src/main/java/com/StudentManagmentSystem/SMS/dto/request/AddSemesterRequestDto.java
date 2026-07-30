package com.StudentManagmentSystem.SMS.dto.request;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddSemesterRequestDto {
    
    @NotBlank(message = "Semester name should not be blank.")
    private String semesterName;
    
    @NotNull(message = "Semester start date is mandatory")
    private LocalDate startDate;
    
    @NotNull(message = "Semester end date is mandatory")
    private LocalDate endDate;
}
