package com.StudentManagmentSystem.SMS.dto.response.adminDashboardResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminStatisticsResponseDto{
    private Long totalStudents;
    private Long totalTeachers;
    private Long totalCourses;
    private Long totalSections;
    private Long totalEnrollments;
    private Long totalCourseOfferings;
}