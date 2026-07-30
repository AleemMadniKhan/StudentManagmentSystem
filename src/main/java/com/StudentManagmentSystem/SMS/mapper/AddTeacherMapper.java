package com.StudentManagmentSystem.SMS.mapper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.StudentManagmentSystem.SMS.dto.request.AddTeacherRequestDto;
import com.StudentManagmentSystem.SMS.dto.response.AddTeacherResponseDto;
import com.StudentManagmentSystem.SMS.dto.response.GetAllTeachersResponseDto;
import com.StudentManagmentSystem.SMS.model.Role;
import com.StudentManagmentSystem.SMS.model.Teacher;
import com.StudentManagmentSystem.SMS.model.User;

@Component
public class AddTeacherMapper {
        
    public static Teacher requestToTeacher(AddTeacherRequestDto request){
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setCreatedAt(LocalDateTime.now());
        user.setRole(Role.ROLE_TEACHER);
        Teacher teacher = new Teacher();
        teacher.setUser(user);
        teacher.setHireDate(LocalDate.now());
        teacher.setDepartment(request.getDepartment());
        teacher.setTeacherId(request.getTeacherId());
        return teacher;
    }

    public static AddTeacherResponseDto TeacherToResponse(Teacher teacher){
        AddTeacherResponseDto response = new AddTeacherResponseDto();
        response.setUsername(teacher.getUser().getUsername());
        response.setDepartment(teacher.getDepartment());
        response.setTeacherId(teacher.getTeacherId());
        response.setCreatedAt(LocalDateTime.now());
        response.setHireDate(LocalDate.now());

        return response;
    }

    public static List<GetAllTeachersResponseDto> getAllTeachersMapper(List<Teacher> teachers) {
        List<GetAllTeachersResponseDto> responses = new ArrayList<>();
        teachers.forEach(teacher -> {
            GetAllTeachersResponseDto response = new GetAllTeachersResponseDto();
             response.setId(teacher.getId());      
            response.setUsername(teacher.getUser().getUsername());
            response.setDepartment(teacher.getDepartment());
            response.setHireDate(teacher.getHireDate());
            response.setTeacherId(teacher.getTeacherId());
            response.setEmail(teacher.getUser().getEmail());
            response.setRole(teacher.getUser().getRole());
            responses.add(response);
        });
        return responses;
    }

        public static GetAllTeachersResponseDto getTeacherByIdMapper(Teacher teacher){
            GetAllTeachersResponseDto response = new GetAllTeachersResponseDto();
            response.setId(teacher.getId());      
            response.setUsername(teacher.getUser().getUsername());
            response.setDepartment(teacher.getDepartment());
            response.setHireDate(teacher.getHireDate());
            response.setTeacherId(teacher.getTeacherId());
            response.setEmail(teacher.getUser().getEmail());
            response.setRole(teacher.getUser().getRole());
        return response;
    }
}
