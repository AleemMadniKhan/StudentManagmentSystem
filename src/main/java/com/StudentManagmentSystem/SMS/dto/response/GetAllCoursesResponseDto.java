package com.StudentManagmentSystem.SMS.dto.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllCoursesResponseDto {
    private Integer id;
    private String courseCode;
    private String courseName;
    private String courseDescription;
    private int credits;
    
}
