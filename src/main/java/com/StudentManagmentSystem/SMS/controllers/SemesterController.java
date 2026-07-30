package com.StudentManagmentSystem.SMS.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.StudentManagmentSystem.SMS.dto.request.AddSemesterRequestDto;
import com.StudentManagmentSystem.SMS.dto.response.GetAllSemesterResponseDto;
import com.StudentManagmentSystem.SMS.exceptions.EntityNotFoundException;
import com.StudentManagmentSystem.SMS.model.Semester;
import com.StudentManagmentSystem.SMS.service.SemesterService;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/semester")
public class SemesterController {
    
    private final SemesterService service;
    public SemesterController(SemesterService service){
        this.service = service;
    }
    @PostMapping("/")
    @PreAuthorize("hasAuthority('CREATE_SEMESTER')")
    public String createSemester(@Valid @RequestBody AddSemesterRequestDto request){
       return service.addSemester(request);
    }

    @GetMapping("/")
    @PreAuthorize("hasAuthority('GET_ALL_SEMESTER')")
    public List<GetAllSemesterResponseDto> getSemester() throws EntityNotFoundException{
        return service.getAllSemsters();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('GET_SEMESTER')")
    public Semester getSemesterById(@PathVariable Integer id){
        return service.getSemesterById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('UPDATE_SEMESTER')")
    public String updateSemester(@PathVariable Integer id, @Valid @RequestBody AddSemesterRequestDto request) 
    throws EntityNotFoundException{
        return service.updateSemester(id, request);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('DELETE_SEMESTER')")
    public String deleteSemester(@PathVariable Integer id){
        return service.deleteSemester(id);
    }    
}
