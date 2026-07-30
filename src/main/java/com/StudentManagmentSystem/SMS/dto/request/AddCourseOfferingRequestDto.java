package com.StudentManagmentSystem.SMS.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AddCourseOfferingRequestDto {
    
    @NotNull(message = "Course id should not be blank")
    private Integer courseId;
    @NotNull(message = "Section id should not be blank")
    private Integer sectionId;
    @NotNull(message = "Teacher id should not be blank")
    private Integer teacherId;
}
