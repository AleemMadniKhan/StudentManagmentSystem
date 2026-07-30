package com.StudentManagmentSystem.SMS.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddCourseResponseDto{
    private String courseCode;
    private String courseName;
}
