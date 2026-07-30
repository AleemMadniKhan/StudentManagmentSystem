package com.StudentManagmentSystem.SMS.mapper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.StudentManagmentSystem.SMS.dto.response.EnrollmentResponseDto;
import com.StudentManagmentSystem.SMS.model.CourseOffering;
import com.StudentManagmentSystem.SMS.model.Enrollment;
import com.StudentManagmentSystem.SMS.model.Student;

public class EnrollmentMapper {
 public static Enrollment requestToEntity(Student student, CourseOffering courseOffering){
    Enrollment enrollment = new Enrollment();
    enrollment.setEnrollmentDate(LocalDate.now());
    enrollment.setStudent(student);
    enrollment.setCourseOffering(courseOffering);
     return enrollment;
 } 

 public static List<EnrollmentResponseDto> entityToResponse(List<Enrollment> enrollments){
    List<EnrollmentResponseDto> responses = new ArrayList<>();

    enrollments.forEach(enrollment ->{ 
    EnrollmentResponseDto response = new EnrollmentResponseDto();
    
    response.setEnrollmentId(enrollment.getId());
    response.setStudentName(enrollment.getStudent().getUser().getUsername());
    response.setStudendRollNumber(enrollment.getStudent().getRollNumber());
    response.setEnrollmentDate(enrollment.getEnrollmentDate());
    response.setCourseOfferingId(enrollment.getCourseOffering().getId());
    
    responses.add(response);
    });
     
    return responses;
 }

  public static EnrollmentResponseDto getEnrollmentResponseByIdMapper(Enrollment enrollment){

    EnrollmentResponseDto response = new EnrollmentResponseDto();
   
    response.setEnrollmentId(enrollment.getId());
    response.setStudentName(enrollment.getStudent().getUser().getUsername());
    response.setStudendRollNumber(enrollment.getStudent().getRollNumber());
    response.setEnrollmentDate(enrollment.getEnrollmentDate());

    return response;
 }
}
