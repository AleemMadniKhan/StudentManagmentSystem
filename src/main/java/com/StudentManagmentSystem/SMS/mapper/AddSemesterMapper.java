package com.StudentManagmentSystem.SMS.mapper;

import com.StudentManagmentSystem.SMS.dto.request.AddSemesterRequestDto;
import com.StudentManagmentSystem.SMS.model.Semester;

public class AddSemesterMapper {
    public static Semester requestToEntity(AddSemesterRequestDto request){
        Semester semester = new Semester();
        semester.setSemesterName(request.getSemesterName());
        semester.setStartDate(request.getStartDate());
        semester.setEndDate(request.getEndDate());

        return semester;
    }
}
