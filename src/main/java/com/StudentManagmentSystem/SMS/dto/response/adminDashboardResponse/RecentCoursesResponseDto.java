package com.StudentManagmentSystem.SMS.dto.response.adminDashboardResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecentCoursesResponseDto {
    private Integer courseId;
    private String courseCode;
    private String courseName;
    private int credits;
}
