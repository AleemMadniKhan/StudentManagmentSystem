package com.StudentManagmentSystem.SMS.dto.response.teacherDashboardResponse;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeacherAssingedCoursesResponseDto{
    private Integer courseId;
    private String courseCode;
    private String courseName;
    private String sectionName;
    private String semesterName;
    private int credits;
    private Integer enrolledStudents;

}