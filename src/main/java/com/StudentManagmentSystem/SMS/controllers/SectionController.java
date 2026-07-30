package com.StudentManagmentSystem.SMS.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.StudentManagmentSystem.SMS.dto.request.AddSectionRequestDto;
import com.StudentManagmentSystem.SMS.dto.response.SectionResponseDto;
import com.StudentManagmentSystem.SMS.service.SectionService;

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
@RequestMapping("/section")
public class SectionController {
    
    private final SectionService service;
    
    public SectionController(SectionService service){
        this.service= service;
    }

    @PostMapping("/")
    @PreAuthorize("hasAuthority('CREATE_SECTION')")
    public String createSection(@Valid @RequestBody AddSectionRequestDto request) {        
        return service.creatSection(request);
    }

    @GetMapping("/")
    @PreAuthorize("hasAuthority('GET_ALL_SECTIONS')")
    public List<SectionResponseDto> getSections() {
        return  service.getAllSections();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('GET_SECTION')")
    public SectionResponseDto getSectionById(@PathVariable Integer id){
        return service.findSectionById(id);
    }    

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('UPDATE_SECTION')")
    public String updateSection(@PathVariable Integer id,@Valid @RequestBody AddSectionRequestDto request){
        return service.updateSection(id, request);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('DELETE_SECTION')")
    public String deleteSection(@PathVariable Integer id){
        return service.deleteSection(id);
    }
    
}
