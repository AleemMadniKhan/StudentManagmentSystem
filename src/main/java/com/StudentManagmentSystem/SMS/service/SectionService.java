package com.StudentManagmentSystem.SMS.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.StudentManagmentSystem.SMS.dto.request.AddSectionRequestDto;
import com.StudentManagmentSystem.SMS.dto.response.SectionResponseDto;
import com.StudentManagmentSystem.SMS.exceptions.EntityNotFoundException;
import com.StudentManagmentSystem.SMS.mapper.SectionMapper;
import com.StudentManagmentSystem.SMS.model.Section;
import com.StudentManagmentSystem.SMS.model.Semester;
import com.StudentManagmentSystem.SMS.repository.SectionRepo;
import com.StudentManagmentSystem.SMS.repository.SemesterRepo;

import jakarta.transaction.Transactional;

@Service
public class SectionService {

    private final SectionRepo sectionRepo;
    private final SemesterRepo semesterRepo;

    public SectionService(SectionRepo sectionRepo, SemesterRepo semesterRepo){
        this.sectionRepo = sectionRepo;
        this.semesterRepo = semesterRepo;
    }
    @Transactional
    public String creatSection(AddSectionRequestDto request) {
        Semester semester = semesterRepo.findById(request.getSemesterId()).orElseThrow(
            () -> new EntityNotFoundException("Semester with id " + request.getSemesterId() + " not found.")
        );
        Section section = SectionMapper.requestToEntity(request,semester);
        
        sectionRepo.save(section);
        return "Section " + section.getName()+ " is created sucessfully.";
    }

    public List<SectionResponseDto> getAllSections(){

        List<Section> sections = sectionRepo.findAll();
        if(sections.isEmpty()){
            throw new EntityNotFoundException("No section found.");
        }
        return SectionMapper.entityToResponse(sections);
    }
    
    public SectionResponseDto findSectionById(Integer id){
        Section section = sectionRepo.findById(id).orElseThrow(
        () -> new EntityNotFoundException("Section with id " + id + " not found.")
        );
        return SectionMapper.getSectionByIdMapper(section);
    }

    @Transactional
	public String updateSection(Integer id, AddSectionRequestDto request) {
        Section section = sectionRepo.findById(id).orElseThrow(
        () -> new EntityNotFoundException("Section with id " + id + " not found.")
        );
        Semester semester = semesterRepo.findById(request.getSemesterId()).orElseThrow(
            () -> new EntityNotFoundException("Semester with id " + request.getSemesterId() + " not found.")
        );
        
        section.setSemester(semester);

        section.setName(request.getName());
        section.setNumberOfSeats(request.getNumberOfSeats());
        sectionRepo.save(section);

        return "Section " + request.getName()+ " is updated sucessfully.";
	}

    public String deleteSection(Integer id){
        if(sectionRepo.existsById(id)){
            sectionRepo.deleteById(id);
            return "Section " + id + " is deleted sucessfully.";
        }
        throw new EntityNotFoundException("Section with id " + id + " not found.");
    }
    
}
