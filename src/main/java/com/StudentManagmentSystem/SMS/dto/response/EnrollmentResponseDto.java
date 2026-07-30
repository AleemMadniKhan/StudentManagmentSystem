package com.StudentManagmentSystem.SMS.dto.response;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnrollmentResponseDto {

    private Integer enrollmentId;

    private LocalDate enrollmentDate;

    private String studendRollNumber;

    private String studentName;
    
    private Integer courseOfferingId;
    
}
