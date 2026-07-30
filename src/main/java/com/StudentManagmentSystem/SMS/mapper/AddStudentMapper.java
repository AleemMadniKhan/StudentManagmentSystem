package com.StudentManagmentSystem.SMS.mapper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.StudentManagmentSystem.SMS.dto.request.AddStudentRequestDto;
import com.StudentManagmentSystem.SMS.dto.response.AddStudentResponseDto;
import com.StudentManagmentSystem.SMS.dto.response.GetAllStudentsResponseDto;
import com.StudentManagmentSystem.SMS.model.Role;
import com.StudentManagmentSystem.SMS.model.Student;
import com.StudentManagmentSystem.SMS.model.User;

public class AddStudentMapper {
    
    public static Student requestToStudent(AddStudentRequestDto request){
        User user = new User();
        
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setCreatedAt(LocalDateTime.now());
        user.setRole(Role.ROLE_STUDENT);
        Student student = new Student();
        student.setUser(user);
        student.setRollNumber(request.getRollNumber());
        student.setEnrollmentDate(LocalDate.now());

        return student;
    }

    public static AddStudentResponseDto studentToResponse(Student student){
        AddStudentResponseDto response = new AddStudentResponseDto();
        response.setUsername(student.getUser().getUsername());
        response.setRollNumber(student.getRollNumber());
        response.setCreatedAt(LocalDateTime.now());
        response.setEnrollmentDate(LocalDate.now());

        return response;
    }

    public static List<GetAllStudentsResponseDto> getAllStudentsMapper(List<Student> students){
        List<GetAllStudentsResponseDto> responses = new ArrayList<>();
        students.forEach(student -> {
            GetAllStudentsResponseDto response = new GetAllStudentsResponseDto();
            response.setUsername(student.getUser().getUsername());
            response.setRollNumber(student.getRollNumber());
            response.setEnrollmentDate(student.getEnrollmentDate());
            response.setStudentId(student.getStudentId());
            response.setEmail(student.getUser().getEmail());
            response.setRole(student.getUser().getRole());
            responses.add(response);
        });
        return responses;
    }

    public static GetAllStudentsResponseDto getStudentByIdMapper(Student student){
            GetAllStudentsResponseDto response = new GetAllStudentsResponseDto();
            response.setUsername(student.getUser().getUsername());
            response.setRollNumber(student.getRollNumber());
            response.setEnrollmentDate(student.getEnrollmentDate());
            response.setStudentId(student.getStudentId());
            response.setEmail(student.getUser().getEmail());
            response.setRole(student.getUser().getRole());
        return response;
    }
    
}
