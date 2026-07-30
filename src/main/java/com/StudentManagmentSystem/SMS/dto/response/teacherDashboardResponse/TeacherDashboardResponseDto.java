package com.StudentManagmentSystem.SMS.dto.response.teacherDashboardResponse;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeacherDashboardResponseDto {
    private Integer teacherId;
    private String employeeId;
    private String teacherName;
    private String email;
    private String department;
    private LocalDate hireDate;

    private Integer numberOfAssignedCourses;
    private Integer numberOfAssignedSections;

    private List<TeacherAssingedCoursesResponseDto> assignedCoursesDetails;
    private List<TeacherDashboardEnrollmentDto> enrollments;

}
