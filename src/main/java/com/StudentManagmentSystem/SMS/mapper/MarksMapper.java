package com.StudentManagmentSystem.SMS.mapper;

import java.util.ArrayList;
import java.util.List;

import com.StudentManagmentSystem.SMS.dto.response.MarksResponseDto;
import com.StudentManagmentSystem.SMS.dto.request.AddMarksRequestDto;
import com.StudentManagmentSystem.SMS.model.Enrollment;
import com.StudentManagmentSystem.SMS.model.Marks;

public class MarksMapper {
    public static Marks requestToEntity(AddMarksRequestDto request, Enrollment enrollment){
        Marks marks = new Marks();
        marks.setAssessmentType(request.getAssessmentType());
        marks.setObtainedMarks(request.getObtainedMarks());
        marks.setTotalMarks(request.getTotalMarks());
        marks.setEnrollment(enrollment);
        marks.setWeightage(request.getWeightage());
        return marks;
    }      
    
 public static List<MarksResponseDto> entityToResponse(List<Marks> marks){
    List<MarksResponseDto> responses = new ArrayList<>();

    marks.forEach(mark ->{ 
    MarksResponseDto response = new MarksResponseDto();
    response.setId(mark.getId());
    response.setAssessmentType(mark.getAssessmentType());
    response.setEnrollmentId(mark.getEnrollment().getId());
    response.setObtainedMarks(mark.getObtainedMarks());
    response.setTotalMarks(mark.getTotalMarks());
    response.setWeightage(mark.getWeightage());
    responses.add(response);
    });
     
    return responses;
 }
 
  public static MarksResponseDto getMarksByIdDto(Marks marks){

    MarksResponseDto response = new MarksResponseDto();
    response.setId(marks.getId());
    response.setAssessmentType(marks.getAssessmentType());
    response.setEnrollmentId(marks.getEnrollment().getId());
    response.setObtainedMarks(marks.getObtainedMarks());
    response.setTotalMarks(marks.getTotalMarks());
    return response;
 }
}
