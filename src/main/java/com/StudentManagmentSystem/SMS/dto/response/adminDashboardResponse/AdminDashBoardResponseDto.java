package com.StudentManagmentSystem.SMS.dto.response.adminDashboardResponse;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminDashBoardResponseDto {
    private Integer id;
    private String username;
    private String email;
    private AdminStatisticsResponseDto statistics;
    private List<RecentStudentsResponseDto> recentStudents;
    private List<RecentTeachersResponseDto> recentTeachers;
    private List<RecentCoursesResponseDto> recentCourses;
}

