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

import com.StudentManagmentSystem.SMS.dto.request.AddTeacherRequestDto;
import com.StudentManagmentSystem.SMS.dto.response.AddTeacherResponseDto;
import com.StudentManagmentSystem.SMS.dto.response.GetAllTeachersResponseDto;
import com.StudentManagmentSystem.SMS.service.TeacherService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/teacher")
public class TeacherController {

    private final TeacherService service;

    public TeacherController(TeacherService service){
        this.service = service;
    }
    
    @PostMapping("/")
    @PreAuthorize("hasAuthority('CREATE_TEACHER')")
    public AddTeacherResponseDto createTeacher(@Valid @RequestBody AddTeacherRequestDto request){
        System.out.println(request);
        return service.addTeacher(request);
    }

    @GetMapping("/")
    
    @PreAuthorize("hasAuthority('GET_ALL_TEACHER')")
    public List<GetAllTeachersResponseDto> getAllTeachers(){
        return service.getAllTeachers();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('GET_TEACHER')")
    public GetAllTeachersResponseDto getTeacherById(@PathVariable Integer id) {
        return service.getTeacherById(id);
    }
    
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('UPDATE_TEACHER')")
    public String updateTeacher(@PathVariable Integer id, @RequestBody AddTeacherRequestDto request) {
        return service.updateTeacher(id, request);
    }
    @DeleteMapping("/{id}") 
    @PreAuthorize("hasAuthority('DELETE_TEACHER')")
    public String deleteTeacher(@PathVariable Integer id) {
        return service.deleteTeacher(id);
    }
}
