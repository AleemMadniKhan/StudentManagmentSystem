package com.StudentManagmentSystem.SMS.mapper;

import com.StudentManagmentSystem.SMS.dto.response.CourseOfferingResponseDto;
import com.StudentManagmentSystem.SMS.model.Course;
import com.StudentManagmentSystem.SMS.model.CourseOffering;
import com.StudentManagmentSystem.SMS.model.Section;
import com.StudentManagmentSystem.SMS.model.Teacher;

import java.util.ArrayList;
import java.util.List;

public class CourseOfferingMapper {

    public static CourseOffering requestToEntity(Course course, Section section, Teacher teacher) {
        CourseOffering offering = new CourseOffering();
        offering.setCourse(course);
        offering.setSection(section);
        offering.setTeacher(teacher);
        return offering;
    }

public static CourseOfferingResponseDto entityToResponse(CourseOffering offering) {
    CourseOfferingResponseDto response = new CourseOfferingResponseDto();
    response.setOfferingId(offering.getId());
    
    if (offering.getCourse() != null) {
        response.setCourseId(offering.getCourse().getId());
        response.setCourseName(offering.getCourse().getCourseName());
    }
    
    if (offering.getSection() != null) {
        response.setSectionId(offering.getSection().getId());
        response.setSectionName(offering.getSection().getName());
    }
    
    if (offering.getTeacher() != null) {
        // Use getTeacherId() if that is the getter name on your Teacher entity
        response.setTeacherId(offering.getTeacher().getId()); 
    }
    
    return response;
}

    public static List<CourseOfferingResponseDto> entityToResponseList(List<CourseOffering> offerings) {
        List<CourseOfferingResponseDto> responses = new ArrayList<>();
        offerings.forEach(offering -> {
        CourseOfferingResponseDto response = new CourseOfferingResponseDto();
        response.setOfferingId(offering.getId());
        response.setCourseId(offering.getCourse().getId());
        response.setCourseName(offering.getCourse().getCourseName());
        response.setSectionId(offering.getSection().getId());
        response.setSectionName(offering.getSection().getName());
        response.setTeacherId(offering.getTeacher().getId());

        responses.add(response);
        });
        return responses;
    }
}
