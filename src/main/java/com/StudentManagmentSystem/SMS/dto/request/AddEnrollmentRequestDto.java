package com.StudentManagmentSystem.SMS.dto.request;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddEnrollmentRequestDto {
    
    private LocalDate enrollmentDate;
    @NotNull(message = "Student id should not be null.")
    private Integer studentId;
    
    @NotNull(message = "Course offering id should not be null.")
    private Integer courseOfferingId;
}
