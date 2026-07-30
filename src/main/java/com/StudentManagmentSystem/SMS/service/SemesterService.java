package com.StudentManagmentSystem.SMS.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.StudentManagmentSystem.SMS.dto.request.AddSemesterRequestDto;
import com.StudentManagmentSystem.SMS.dto.response.GetAllSemesterResponseDto;
import com.StudentManagmentSystem.SMS.exceptions.EntityNotFoundException;
import com.StudentManagmentSystem.SMS.mapper.AddSemesterMapper;
import com.StudentManagmentSystem.SMS.mapper.GetAllSemesterMapper;
import com.StudentManagmentSystem.SMS.model.Semester;
import com.StudentManagmentSystem.SMS.repository.SemesterRepo;

@Service
public class SemesterService {

    private final SemesterRepo repo;
    public SemesterService(SemesterRepo repo){
        this.repo = repo;
    }

    public String addSemester(AddSemesterRequestDto request) {
        Semester semester = AddSemesterMapper.requestToEntity(request);
        repo.save(semester);
        return "Semester " + semester.getSemesterName() +  " is added successfully.";
    }

    public List<GetAllSemesterResponseDto> getAllSemsters() throws EntityNotFoundException{
       List<Semester> semesters =  repo.findAll();
       if(semesters == null){
        throw new EntityNotFoundException("Semester not Found.");
       }
       return GetAllSemesterMapper.entityToResponse(semesters);
    }
    
    public String updateSemester(Integer id, AddSemesterRequestDto request) throws EntityNotFoundException{
        Semester semester = repo.findById(id).orElseThrow(
            () ->  new EntityNotFoundException("Semester With id "+ id + " not Found.")
            );
            semester.setSemesterName(request.getSemesterName());
            semester.setStartDate(request.getStartDate());
            semester.setEndDate(request.getEndDate());

            repo.save(semester);
            return "Semester Updated successfully.";
    }

    public Semester getSemesterById(Integer id) {
       Semester semester =  repo.findById(id).orElseThrow(
        () -> new EntityNotFoundException("Semester not Found.")
       );
return semester;
    }

    public String deleteSemester(Integer id) {
       if (repo.existsById(id)) 
       
        repo.deleteById(id);

       else

       throw new EntityNotFoundException("Semester not Found.");

       return "Semester with id " + id + " deleted sucessfully.";
    }
    
}
