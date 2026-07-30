package com.StudentManagmentSystem.SMS.controllers;

import com.StudentManagmentSystem.SMS.service.DashboardService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.StudentManagmentSystem.SMS.dto.response.adminDashboardResponse.AdminDashBoardResponseDto;
import com.StudentManagmentSystem.SMS.dto.response.studentDashboardResponse.StudentDashboardResponseDto;
import com.StudentManagmentSystem.SMS.dto.response.teacherDashboardResponse.TeacherDashboardResponseDto;


@RestController
@RequestMapping("/dashboard")
public class DashboardController {
    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService){
        this.dashboardService = dashboardService;
    }
    @GetMapping("/student")
    public StudentDashboardResponseDto studentDashboard(){
        return dashboardService.getStudentDashboard();
    }

    @GetMapping("/teacher")
    public TeacherDashboardResponseDto teacherDashboard() {
        return dashboardService.getTeacherDashboard();
    }

    @GetMapping("/admin")
    public AdminDashBoardResponseDto adminDashboard() {
        
        return dashboardService.adminDashboard();
    }
    
}
