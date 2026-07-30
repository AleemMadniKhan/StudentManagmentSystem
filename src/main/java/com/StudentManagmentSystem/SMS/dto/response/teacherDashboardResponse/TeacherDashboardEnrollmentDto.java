package com.StudentManagmentSystem.SMS.dto.response.teacherDashboardResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeacherDashboardEnrollmentDto {
    private Integer enrollmentId;
    private String studentRollNumber;
    private String studentName;
    private String studentEmail;
    private String courseCode;
    private String sectionName;
}
