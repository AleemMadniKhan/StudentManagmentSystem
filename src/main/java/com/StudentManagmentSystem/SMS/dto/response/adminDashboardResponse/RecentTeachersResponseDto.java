package com.StudentManagmentSystem.SMS.dto.response.adminDashboardResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecentTeachersResponseDto {
    private Integer teacherId;
    private String employeeId;
    private String teacherName;
    private String department;
}
