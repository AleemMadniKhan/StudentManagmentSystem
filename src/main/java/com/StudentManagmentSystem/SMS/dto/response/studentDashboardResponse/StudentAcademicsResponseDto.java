package com.StudentManagmentSystem.SMS.dto.response.studentDashboardResponse;

import java.util.List;

import com.StudentManagmentSystem.SMS.dto.response.CourseAssessmentMarksResponseDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentAcademicsResponseDto {
    
    private String courseCode;

    private String courseName;

    private Integer credits;

    private Double gradePoint;

    private Double coursePercentage;

    private String instructorName;

    private List<CourseAssessmentMarksResponseDto> assessmentMarks;
    
}