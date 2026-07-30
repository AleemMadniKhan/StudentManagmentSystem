package com.StudentManagmentSystem.SMS.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MarksResponseDto {
    
    private Integer id;
    
    private Double totalMarks;

    private Double obtainedMarks;

    private String assessmentType;

    private Integer enrollmentId;

    private Double weightage;
}
