package com.StudentManagmentSystem.SMS.dto.response.adminDashboardResponse;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecentStudentsResponseDto {
    private Integer studentId;
    private String rollNumber;
    private String studentName;
    private LocalDate enrollmentDate;

}
