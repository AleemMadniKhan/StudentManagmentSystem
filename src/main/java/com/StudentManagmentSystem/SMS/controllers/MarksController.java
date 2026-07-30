package com.StudentManagmentSystem.SMS.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.StudentManagmentSystem.SMS.dto.request.AddMarksRequestDto;
import com.StudentManagmentSystem.SMS.dto.response.MarksResponseDto;
import com.StudentManagmentSystem.SMS.service.MarksService;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/marks")
public class MarksController {

    private final MarksService service;

    public MarksController(MarksService service){
        this.service = service;
    }
    @PostMapping("/")
    @PreAuthorize("hasAuthority('ADD_MARKS')")
    public String addMarks(@RequestBody AddMarksRequestDto request) {
        return service.addMarks(request);
    }

    @GetMapping("/")
    @PreAuthorize("hasAuthority('GET_ALL_MARKS')")
    public List<MarksResponseDto> getMarks(){
        return service.getAllMarks();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('GET_MARKS')")
    public MarksResponseDto getMarksById(@PathVariable Integer id){
        return service.getMarksById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('UPDATE_MARKS')")
    public String updateMarks(@PathVariable Integer id, @RequestBody AddMarksRequestDto request) {
        return service.updateMarks(id, request);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('DELETE_MARKS')")
    public String deleteMarks(@PathVariable Integer id){
        return service.deleteMarks(id);
    }
    
}
