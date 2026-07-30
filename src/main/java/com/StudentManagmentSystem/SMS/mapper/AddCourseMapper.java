package com.StudentManagmentSystem.SMS.mapper;

import org.springframework.stereotype.Component;

import com.StudentManagmentSystem.SMS.dto.request.AddCourseRequestDto;
import com.StudentManagmentSystem.SMS.dto.response.AddCourseResponseDto;
import com.StudentManagmentSystem.SMS.model.Course;

@Component
public class AddCourseMapper {
    public static Course requestToCourse(AddCourseRequestDto request){
        Course course = new Course();
        course.setCourseCode(request.getCourseCode());
        course.setCourseName(request.getCourseName());
        course.setCourseDescription(request.getCourseDescription());
        course.setCredits(request.getCredits());
        return course;
    }
    public static AddCourseResponseDto courseToResponse(Course course){
        AddCourseResponseDto response = new AddCourseResponseDto();
        response.setCourseCode(course.getCourseCode());
        response.setCourseName(course.getCourseName());
        return response;
    }
}
