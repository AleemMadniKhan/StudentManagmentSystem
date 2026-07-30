package com.StudentManagmentSystem.SMS.service;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.StudentManagmentSystem.SMS.dto.request.AddStudentRequestDto;
import com.StudentManagmentSystem.SMS.dto.response.AddStudentResponseDto;
import com.StudentManagmentSystem.SMS.dto.response.GetAllStudentsResponseDto;
import com.StudentManagmentSystem.SMS.exceptions.BadRequestException;
import com.StudentManagmentSystem.SMS.exceptions.EntityNotFoundException;
import com.StudentManagmentSystem.SMS.mapper.AddStudentMapper;
import com.StudentManagmentSystem.SMS.model.Student;
import com.StudentManagmentSystem.SMS.repository.StudentRepo;
import com.StudentManagmentSystem.SMS.repository.UserRepo;


@Service
public class StudentService {

    private final PasswordEncoder encoder;
    private final UserRepo userRepo;
    private final StudentRepo studentRepo;

    public StudentService(UserRepo userRepo, PasswordEncoder encoder, StudentRepo studentRepo){
        this.encoder = encoder;
        this.studentRepo = studentRepo;
        this.userRepo = userRepo;
    }

    public AddStudentResponseDto addStudent(AddStudentRequestDto request){

         if (userRepo.existsByEmail(request.getEmail())) {
            throw new BadRequestException("Email already exists.");
         } 

         if (studentRepo.existsByRollNumber(request.getRollNumber())) {
            throw new BadRequestException("Roll Number already exists.");
         }  

        Student student = AddStudentMapper.requestToStudent(request);

        student.getUser().setPassword(encoder.encode(request.getPassword()));
 
        studentRepo.save(student);
        AddStudentResponseDto response = AddStudentMapper.studentToResponse(student);
        return response;
    } 

    public List<GetAllStudentsResponseDto> getAllStudents(){
        List<Student> students  = studentRepo.findAll();
        if (students.isEmpty() || students == null) {
            throw new EntityNotFoundException("No students found.");
        }
        return AddStudentMapper.getAllStudentsMapper(students);
    }

    public GetAllStudentsResponseDto getStudentById(Integer id) {
        Student student =  studentRepo.findById(id).orElseThrow(
           () -> new EntityNotFoundException("Semester not Found.")
       );
        return AddStudentMapper.getStudentByIdMapper(student);
    }

    public String updateStudent(Integer id, AddStudentRequestDto request) throws EntityNotFoundException{
        Student student =  studentRepo.findById(id).orElseThrow(
           () -> new EntityNotFoundException("Student not Found.")
       );
            student.getUser().setUsername(request.getUsername());
            student.getUser().setEmail(request.getEmail());
            student.setRollNumber(request.getRollNumber());

            studentRepo.save(student);
            return "Student Updated successfully.";
    }

        public String deleteStudent(Integer id) {
       if (studentRepo.existsById(id)) 
        studentRepo.deleteById(id);
       else
       throw new EntityNotFoundException("Student not Found.");
       return "Student with id " + id + " deleted sucessfully.";
    }

}
