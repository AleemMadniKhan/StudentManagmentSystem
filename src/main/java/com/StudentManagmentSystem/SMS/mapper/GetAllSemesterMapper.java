package com.StudentManagmentSystem.SMS.mapper;

import java.util.ArrayList;
import java.util.List;

import com.StudentManagmentSystem.SMS.dto.response.GetAllSemesterResponseDto;
import com.StudentManagmentSystem.SMS.model.Semester;

public class GetAllSemesterMapper {
    public static List<GetAllSemesterResponseDto> entityToResponse(List<Semester> semesters){
        List<GetAllSemesterResponseDto> responseDtos = new ArrayList<>();
        for (Semester semester : semesters) {
            GetAllSemesterResponseDto response = new GetAllSemesterResponseDto();
            response.setSemesterName(semester.getSemesterName());
            response.setStartDate(semester.getStartDate());
            response.setEndDate(semester.getEndDate());
            response.setId(semester.getId());
            responseDtos.add(response);
            
        }
        return responseDtos;
    }
}
