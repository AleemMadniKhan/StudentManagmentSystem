package com.StudentManagmentSystem.SMS.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.StudentManagmentSystem.SMS.dto.request.AddEnrollmentRequestDto;
import com.StudentManagmentSystem.SMS.dto.response.EnrollmentResponseDto;
import com.StudentManagmentSystem.SMS.service.EnrollmentService;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/enrollment")
public class EnrollmentController{
    
    private final EnrollmentService service;

    public EnrollmentController(EnrollmentService service){
        this.service = service;
    }

    @PostMapping("/")
    @PreAuthorize("hasAuthority('CREATE_ENROLLMENT')")
    public String createEnrollment(@Valid @RequestBody AddEnrollmentRequestDto request) {
        return service.createEnrollment(request);
    }

    @GetMapping("/")
    @PreAuthorize("hasAuthority('GET_ALL_ENROLLMENT')")
    public List<EnrollmentResponseDto> getEnrollment(){
        return service.getAllEnrollment();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('GET_ENROLLMENT')")
    public EnrollmentResponseDto getEnrollmentById(@PathVariable Integer id){
        return service.findEnrollment(id);
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('UPDATE_ENROLLMENT')")
    public String updateEnrollment(@PathVariable Integer id,@Valid @RequestBody AddEnrollmentRequestDto request){
        return service.updateEnrollment(id, request);
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('DELETE_ENROLLMENT')")
    public String deleteEnrollment(@PathVariable Integer id){
        return service.deleteEnrollment(id);
    }
    
}
