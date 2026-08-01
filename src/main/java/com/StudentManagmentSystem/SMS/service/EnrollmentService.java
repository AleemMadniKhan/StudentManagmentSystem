package com.StudentManagmentSystem.SMS.service;

import java.util.Collections;

import com.StudentManagmentSystem.SMS.repository.CourseOfferingRepo;

import java.util.List;

import org.springframework.stereotype.Service;

import com.StudentManagmentSystem.SMS.dto.request.AddEnrollmentRequestDto;
import com.StudentManagmentSystem.SMS.dto.response.EnrollmentResponseDto;
import com.StudentManagmentSystem.SMS.exceptions.BadRequestException;
import com.StudentManagmentSystem.SMS.exceptions.EntityNotFoundException;
import com.StudentManagmentSystem.SMS.mapper.EnrollmentMapper;
import com.StudentManagmentSystem.SMS.model.CourseOffering;
import com.StudentManagmentSystem.SMS.model.Enrollment;
import com.StudentManagmentSystem.SMS.model.Student;
import com.StudentManagmentSystem.SMS.repository.EnrollmentRepo;
import com.StudentManagmentSystem.SMS.repository.StudentRepo;

import jakarta.transaction.Transactional;

@Service
public class EnrollmentService {

    private final CourseOfferingRepo courseOfferingRepo;
    private final EnrollmentRepo repo;
    private final StudentRepo studentRepo;
    public EnrollmentService(EnrollmentRepo repo, StudentRepo studentRepo, CourseOfferingRepo courseOfferingRepo){
        this.repo = repo;
        this.studentRepo = studentRepo;
        this.courseOfferingRepo = courseOfferingRepo;
    }

    @Transactional
    public String createEnrollment(AddEnrollmentRequestDto request) {

        if (repo.existsByStudent_studentIdAndCourseOffering_id(request.getStudentId(), request.getCourseOfferingId())) {
            throw new BadRequestException("Enrollment Already exists");
        }

        CourseOffering courseOffering = courseOfferingRepo.findById(request.getCourseOfferingId()).orElseThrow(
            () -> new BadRequestException("Course Offering not found.")
        );
        Student student = studentRepo.findById(request.getStudentId()).orElseThrow(
            () -> new BadRequestException("Student not found.")
        );

        Enrollment enrollment = EnrollmentMapper.requestToEntity(student, courseOffering);
        repo.save(enrollment);

        return "Student " + student.getRollNumber() + " is enrolled in course " +courseOffering.getCourse().getCourseCode() + " sucessfully.";
    }

    public List<EnrollmentResponseDto> getAllEnrollment() {
        List<Enrollment> enrollment = repo.findAll();
        if (enrollment.isEmpty()) {
            return Collections.emptyList();
        }
        List<EnrollmentResponseDto> response = EnrollmentMapper.entityToResponse(enrollment);

        return response;
    }

    public EnrollmentResponseDto findEnrollment(Integer id) {
        Enrollment enrollment = repo.findById(id).orElseThrow(
           () -> new EntityNotFoundException("There is no enrollments.")
        );
        return EnrollmentMapper.getEnrollmentResponseByIdMapper(enrollment);
    }

    @Transactional
    public String updateEnrollment(Integer id, AddEnrollmentRequestDto request) {

        Student student = studentRepo.findById(request.getStudentId()).orElseThrow(
            () -> new BadRequestException("Student not found.")
        );

        Enrollment enrollment = repo.findById(id).orElseThrow(
            () -> new EntityNotFoundException("Enrollment not found with id " + id)
        );
        
        enrollment.setStudent(student);
        enrollment.setEnrollmentDate(request.getEnrollmentDate());
        repo.save(enrollment);
        return "Enrollment with id " + enrollment.getId() + " updated.";
    }

    public String deleteEnrollment(Integer id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return "Enrollment with id " + id + " deleted.";
        }
        throw new EntityNotFoundException("Enrollment not found with id " + id);
    }


    
}
