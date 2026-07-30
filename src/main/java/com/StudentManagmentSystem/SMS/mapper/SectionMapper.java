package com.StudentManagmentSystem.SMS.mapper;

import java.util.ArrayList;
import java.util.List;

import com.StudentManagmentSystem.SMS.dto.request.AddSectionRequestDto;
import com.StudentManagmentSystem.SMS.dto.response.SectionResponseDto;
import com.StudentManagmentSystem.SMS.model.Section;
import com.StudentManagmentSystem.SMS.model.Semester;

public class SectionMapper {
    public static Section requestToEntity(AddSectionRequestDto request,Semester semester){
        Section section = new Section();
        section.setName(request.getName());
        section.setSemester(semester);
        section.setNumberOfSeats(request.getNumberOfSeats());

        return section;
    }
    public static List<SectionResponseDto> entityToResponse(List<Section> sections){
        List<SectionResponseDto> responses = new ArrayList<>();
        sections.forEach(section -> {
        SectionResponseDto response = new SectionResponseDto();
        response.setId(section.getId());
        response.setName(section.getName());
        response.setNumberOfSeats(section.getNumberOfSeats());
        response.setSemesterId(section.getSemester().getId());
        response.setSemesterName(section.getSemester().getSemesterName());
        responses.add(response);
        });
        return responses; 
    }
    public static SectionResponseDto getSectionByIdMapper(Section section){
        SectionResponseDto response = new SectionResponseDto();
        response.setName(section.getName());
        response.setId(section.getId());
        response.setNumberOfSeats(section.getNumberOfSeats());
        response.setSemesterId(section.getSemester().getId());
        response.setSemesterName(section.getSemester().getSemesterName());
        return response;
    }
}

