package com.StudentManagmentSystem.SMS.controllers;

import com.StudentManagmentSystem.SMS.service.CourseOfferingService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RestController;

import com.StudentManagmentSystem.SMS.dto.request.AddCourseOfferingRequestDto;
import com.StudentManagmentSystem.SMS.dto.response.CourseOfferingResponseDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/course-offering")
public class CourseOfferingController {

    private final CourseOfferingService service;

    public CourseOfferingController(CourseOfferingService service){
        this.service = service;
    }
 
    @PostMapping("/")
    @PreAuthorize("hasAuthority('CREATE_COURSE_OFFERING')")
    public String addCourseOffering(@Valid @RequestBody AddCourseOfferingRequestDto request) {
       return service.addCourseOffering(request);
    }

    @GetMapping("/{sectionId}")
    @PreAuthorize("hasAuthority('GET_COURSES_OFFERING')")
    public List<CourseOfferingResponseDto> getCoursesBySection(@PathVariable Integer sectionId) {
        return service.getCoursesBySection(sectionId);
    }
    
    @GetMapping("/")
    @PreAuthorize("hasAuthority('GET_ALL_COURSES_OFFERING')")
    public List<CourseOfferingResponseDto> getAllCourseOffering() {
        return service.getAllCourseOffering();
    }
}
