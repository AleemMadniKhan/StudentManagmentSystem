package com.StudentManagmentSystem.SMS.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseAssessmentMarksResponseDto {
    private String assessmentType;   
    private Double obtainedMarks;
    private Double totalMarks;
    private Double weightage;
}
