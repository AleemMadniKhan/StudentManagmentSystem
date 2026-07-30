package com.StudentManagmentSystem.SMS.dto.response.studentDashboardResponse;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDashboardResponseDto {
   private Integer studentId;
   private String studentName;
   private String email;
   private String  rollNumber;

   private Integer semesterId;
   private String semesterName;

   private Double gpa;
   private int completedCredits;
   private int registeredCourses;

  private List<StudentAcademicsResponseDto> courses;
}
