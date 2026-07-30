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

import com.StudentManagmentSystem.SMS.dto.request.AddStudentRequestDto;
import com.StudentManagmentSystem.SMS.dto.response.AddStudentResponseDto;
import com.StudentManagmentSystem.SMS.dto.response.GetAllStudentsResponseDto;
import com.StudentManagmentSystem.SMS.service.StudentService;

import jakarta.validation.Valid;


@RestController 
@RequestMapping("/student")
public class StudentController {
    private final StudentService service;

    public StudentController(StudentService service){
        this.service = service;
    }

    @PostMapping("/")
    @PreAuthorize("hasAuthority('CREATE_STUDENT')")
    public AddStudentResponseDto createStudent(@Valid @RequestBody AddStudentRequestDto request){
        return service.addStudent(request);
    }

    @GetMapping("/")
    @PreAuthorize("hasAuthority('GET_All_STUDENT')")
    public List<GetAllStudentsResponseDto> getAllStudents(){
        return service.getAllStudents();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('GET_STUDENT')")
    public GetAllStudentsResponseDto getStudentById(@PathVariable Integer id) {
        return service.getStudentById(id);
    }
    
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('UPDATE_STUDENT')")
    public String updateStudent(@PathVariable Integer id, @RequestBody AddStudentRequestDto request) {
        return service.updateStudent(id, request);
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('DELETE_STUDENT')")
    public String deleteStudent(@PathVariable Integer id) {
        return service.deleteStudent(id);
    }
    


}
