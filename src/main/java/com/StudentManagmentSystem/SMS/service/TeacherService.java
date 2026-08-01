package com.StudentManagmentSystem.SMS.service;

import java.util.Collections;
import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.StudentManagmentSystem.SMS.dto.request.AddTeacherRequestDto;
import com.StudentManagmentSystem.SMS.dto.response.AddTeacherResponseDto;
import com.StudentManagmentSystem.SMS.dto.response.GetAllTeachersResponseDto;
import com.StudentManagmentSystem.SMS.exceptions.BadRequestException;
import com.StudentManagmentSystem.SMS.exceptions.EntityNotFoundException;
import com.StudentManagmentSystem.SMS.mapper.AddTeacherMapper;
import com.StudentManagmentSystem.SMS.model.Teacher;
import com.StudentManagmentSystem.SMS.repository.TeacherRepo;
import com.StudentManagmentSystem.SMS.repository.UserRepo;


@Service
public class TeacherService {

    private final TeacherRepo teacherRepo;
    private final PasswordEncoder encoder;
    private final UserRepo userRepo;
    public TeacherService(UserRepo userRepo, TeacherRepo teacherRepo, PasswordEncoder encoder){
        this.userRepo = userRepo;
        this.encoder = encoder;
        this.teacherRepo = teacherRepo;
    }

    public AddTeacherResponseDto addTeacher(AddTeacherRequestDto request){
        System.out.println(request);
        if (userRepo.existsByEmail(request.getEmail())) {
            throw new BadRequestException("Email already exists.");
        } 
        if (teacherRepo.existsByTeacherId(request.getTeacherId())) {
            throw new BadRequestException("Roll Number already exists.");
        }
        
        Teacher teacher = AddTeacherMapper.requestToTeacher(request);

        teacher.getUser().setPassword(encoder.encode(request.getPassword()));
        teacherRepo.save(teacher);
        AddTeacherResponseDto reponse = AddTeacherMapper.TeacherToResponse(teacher);
        return reponse;
    }

        public List<GetAllTeachersResponseDto> getAllTeachers(){
        List<Teacher> teachers  = teacherRepo.findAll();
        if (teachers.isEmpty() || teachers == null) {
            return Collections.emptyList();
        }
        return AddTeacherMapper.getAllTeachersMapper(teachers);
    }

    public GetAllTeachersResponseDto getTeacherById(Integer id) {
        Teacher teacher =  teacherRepo.findById(id).orElseThrow(
           () -> new EntityNotFoundException("Semester not Found.")
       );
        return AddTeacherMapper.getTeacherByIdMapper(teacher);
    }

    public String updateTeacher(Integer id, AddTeacherRequestDto request) throws EntityNotFoundException{
        Teacher teacher =  teacherRepo.findById(id).orElseThrow(
           () -> new EntityNotFoundException("Teacher not Found.")
       );
            teacher.getUser().setUsername(request.getUsername());
            teacher.getUser().setEmail(request.getEmail());
            teacher.setDepartment(request.getDepartment());

            teacherRepo.save(teacher);
            return "Teacher Updated successfully.";
    }

    public String deleteTeacher(Integer id) {
       if (teacherRepo.existsById(id)) {
        teacherRepo.deleteById(id);
       }
        
       else{
        throw new EntityNotFoundException("Teacher not Found.");
       }
       return "Teacher with id " + id + " deleted sucessfully.";
    }

}
