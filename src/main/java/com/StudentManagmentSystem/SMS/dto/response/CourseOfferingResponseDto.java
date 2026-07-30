package com.StudentManagmentSystem.SMS.dto.response;

import lombok.Data;

@Data
public class CourseOfferingResponseDto {
    private Integer offeringId;
    private Integer courseId;
    private String courseName;
    private Integer sectionId;
    private String sectionName;
    private Integer teacherId;
}
