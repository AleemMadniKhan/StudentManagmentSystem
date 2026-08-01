package com.StudentManagmentSystem.SMS.controllers;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.StudentManagmentSystem.SMS.dto.request.AddCourseRequestDto;
import com.StudentManagmentSystem.SMS.dto.response.AddCourseResponseDto;
import com.StudentManagmentSystem.SMS.dto.response.GetAllCoursesResponseDto;
import com.StudentManagmentSystem.SMS.exceptions.BadRequestException;
import com.StudentManagmentSystem.SMS.model.Course;
import com.StudentManagmentSystem.SMS.service.CourseService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/course")
public class CourseController {

    private final CourseService service;

    public CourseController(CourseService service) {
        this.service = service;
    }
    
    @PostMapping("/")
    @PreAuthorize("hasAuthority('CREATE_COURSE')")
    public AddCourseResponseDto createCourse(@Valid @RequestBody AddCourseRequestDto request) throws BadRequestException {
        return service.createCourse(request);
    }
    
    @GetMapping("/")
    @PreAuthorize("hasAuthority('GET_ALL_COURSES')")
    public List<GetAllCoursesResponseDto> getCourses() {
        return service.getAllCources();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('UPDATE_COURSE')")
    public String updateCourse(@PathVariable Integer id, @Valid @RequestBody Course course) {
        return service.updateCourse(id, course);
    }
    
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('GET_COURSE')")
    public Course getCourseById(@PathVariable Integer id) {
        return service.getCourseById(id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('DELETE_COURSE')")
    public String deleteCourse(@PathVariable Integer id) {
        return service.deleteCourse(id);
    }
}