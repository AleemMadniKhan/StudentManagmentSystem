package com.StudentManagmentSystem.SMS.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.StudentManagmentSystem.SMS.dto.response.GetAllCoursesResponseDto;
import com.StudentManagmentSystem.SMS.model.Course;

@Component
public class GetAllCourcesMapper {
    public List<GetAllCoursesResponseDto> courseToResponse(List<Course> courses){
        List<GetAllCoursesResponseDto> responseDto = new ArrayList<>();
        for(Course course:courses){
            GetAllCoursesResponseDto response = new GetAllCoursesResponseDto();
            response.setId(course.getId());
            response.setCourseCode(course.getCourseCode());
            response.setCourseName(course.getCourseName());
            response.setCourseDescription(course.getCourseDescription());
            response.setCredits(course.getCredits());
            responseDto.add(response);
        }
        return responseDto;
    }
}
