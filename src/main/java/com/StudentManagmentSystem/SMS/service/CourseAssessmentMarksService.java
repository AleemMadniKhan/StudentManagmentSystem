package com.StudentManagmentSystem.SMS.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.StudentManagmentSystem.SMS.dto.response.CourseAssessmentMarksResponseDto;
import com.StudentManagmentSystem.SMS.model.Marks;
import com.StudentManagmentSystem.SMS.repository.MarksRepo;

@Service
public class CourseAssessmentMarksService {
    private final MarksRepo marksRepo;
    public CourseAssessmentMarksService(MarksRepo marksRepo){
        this.marksRepo = marksRepo;
    }
    public List<CourseAssessmentMarksResponseDto> getAssemssmentMarks(Integer enrollmentId){

        List<CourseAssessmentMarksResponseDto> courseAssemesssmentsMarks = new ArrayList<>();

            List<Marks> allMarks = marksRepo.findAllByEnrollment_Id(enrollmentId);

            for (Marks mark : allMarks) {
                if (mark.getObtainedMarks() != null && mark.getTotalMarks() != null && mark.getWeightage() != null) {
                    if(mark.getTotalMarks() <= 0) continue;
                    CourseAssessmentMarksResponseDto courseAssemesssmentMarks = new CourseAssessmentMarksResponseDto();

                    courseAssemesssmentMarks.setAssessmentType(mark.getAssessmentType());
                    courseAssemesssmentMarks.setWeightage(mark.getWeightage());
                    courseAssemesssmentMarks.setObtainedMarks(mark.getObtainedMarks());
                    courseAssemesssmentMarks.setTotalMarks(mark.getTotalMarks());
                    courseAssemesssmentsMarks.add(courseAssemesssmentMarks);
                    }
                }

     
            return courseAssemesssmentsMarks;
       }
}