package com.StudentManagmentSystem.SMS.service;

import com.StudentManagmentSystem.SMS.repository.EnrollmentRepo;

import java.util.List;

import org.springframework.stereotype.Service;

import com.StudentManagmentSystem.SMS.dto.request.AddMarksRequestDto;
import com.StudentManagmentSystem.SMS.dto.response.MarksResponseDto;
import com.StudentManagmentSystem.SMS.exceptions.BadRequestException;
import com.StudentManagmentSystem.SMS.mapper.MarksMapper;
import com.StudentManagmentSystem.SMS.model.CourseOffering;
import com.StudentManagmentSystem.SMS.model.Enrollment;
import com.StudentManagmentSystem.SMS.model.Marks;
import com.StudentManagmentSystem.SMS.repository.MarksRepo;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class MarksService {

    private final EnrollmentRepo enrollmentRepo;
    private final MarksRepo repo;
    public MarksService(MarksRepo repo, EnrollmentRepo enrollmentRepo){
        this.repo = repo;
        this.enrollmentRepo = enrollmentRepo;
    }

        public double getTotalWeightage(Enrollment enrollment, double weightage){
        
            CourseOffering offering = enrollment.getCourseOffering();

            if(offering == null || offering.getCourse() == null) throw new EntityNotFoundException("Course offering is null.");

            List<Marks> allMarks = repo.findAllByEnrollment_Id(enrollment.getId());
            double totalWeightage = weightage;

            for (Marks mark : allMarks) {
                if (mark.getWeightage() != null) {
                    totalWeightage += mark.getWeightage();
                }
            }
        return totalWeightage;
    }

    public String addMarks(AddMarksRequestDto request) {
        if (request.getObtainedMarks() > request.getTotalMarks() || request.getObtainedMarks() < 0) {
            throw new BadRequestException("Invalid obtained marks."); 
        }
        
        Enrollment enrollment =  enrollmentRepo.findById(request.getEnrollmentId()).orElseThrow(
            () -> new BadRequestException("Enrollment not found.")
        );
        double totalWeightage = getTotalWeightage(enrollment, request.getWeightage());
        if (totalWeightage > 100) {
                throw new BadRequestException("Weightage overflow.");
        }

        enrollment.getCourseOffering();
        Marks marks = MarksMapper.requestToEntity(request, enrollment);
        repo.save(marks);
        return "Marks added.";
    }

    public List<MarksResponseDto> getAllMarks(){
        List<Marks> marks = repo.findAll();
        if(marks.isEmpty()){
            throw new EntityNotFoundException("Marks not Found");
        }
        return MarksMapper.entityToResponse(marks);
    }

    public MarksResponseDto getMarksById(Integer id) {
        Marks marks = repo.findById(id).orElseThrow(
            () -> new BadRequestException("Marks not found.")
        );
        return MarksMapper.getMarksByIdDto(marks);
    }

    @Transactional
    public String updateMarks(Integer id, AddMarksRequestDto request) {
        Enrollment enrollment =  enrollmentRepo.findById(request.getEnrollmentId()).orElseThrow(
            () -> new BadRequestException("Enrollment not found.")
        );
        Marks marks = repo.findById(id).orElseThrow(
            () -> new BadRequestException("Marks not found.")
        );

        double totalWeightage = getTotalWeightage(enrollment, request.getWeightage());
        if (totalWeightage - marks.getWeightage() > 100) {
                throw new BadRequestException("Weightage overflow.");
        }
        marks.setAssessmentType(request.getAssessmentType());
        marks.setEnrollment(enrollment);
        marks.setObtainedMarks(request.getObtainedMarks());
        marks.setTotalMarks(request.getTotalMarks());
        marks.setWeightage(request.getWeightage());
        return "Marks for enrollment " + enrollment.getId() + " updated Sucessfully";
    }

    public String deleteMarks(Integer id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return "Marks with id" + id + " deleted sucessfully.";
        }        
        throw new BadRequestException("Marks with id " + id + " not found.");
    }
    


}
