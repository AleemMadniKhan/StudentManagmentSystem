package com.StudentManagmentSystem.SMS.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddCourseRequestDto {
    
    
    @NotBlank(message = "Course code should not be blank")
    private String courseCode;

    @NotBlank(message = "Course name should not be blank")
    private String courseName;

    @NotBlank(message = "Course description should not be blank")
    private String courseDescription;

    @NotNull(message = "Course credits should not be null")
    private int credits;     
    
}
