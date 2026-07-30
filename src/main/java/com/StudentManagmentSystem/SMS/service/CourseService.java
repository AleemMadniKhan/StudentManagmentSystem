package com.StudentManagmentSystem.SMS.service;

import com.StudentManagmentSystem.SMS.mapper.GetAllCourcesMapper;
import java.util.List;

import org.springframework.stereotype.Service;

import com.StudentManagmentSystem.SMS.dto.request.AddCourseRequestDto;
import com.StudentManagmentSystem.SMS.dto.response.AddCourseResponseDto;
import com.StudentManagmentSystem.SMS.dto.response.GetAllCoursesResponseDto;
import com.StudentManagmentSystem.SMS.exceptions.BadRequestException;
import com.StudentManagmentSystem.SMS.exceptions.EntityNotFoundException;
import com.StudentManagmentSystem.SMS.mapper.AddCourseMapper;
import com.StudentManagmentSystem.SMS.model.Course;
import com.StudentManagmentSystem.SMS.repository.CourseRepo;

@Service
public class CourseService {
    
    private final GetAllCourcesMapper getAllCourcesMapper;
    private final CourseRepo repo;
    
    public CourseService(CourseRepo repo, GetAllCourcesMapper getAllCourcesMapper){
        this.repo = repo;
        this.getAllCourcesMapper = getAllCourcesMapper;
    }

    public AddCourseResponseDto createCourse(AddCourseRequestDto request) throws BadRequestException{
        if (repo.existsByCourseCode(request.getCourseCode())) {
            throw new BadRequestException("Course Already Exists.");
        }
        Course course = AddCourseMapper.requestToCourse(request);

        repo.save(course);
        return AddCourseMapper.courseToResponse(course);
    }

    public List<GetAllCoursesResponseDto> getAllCources() throws EntityNotFoundException{
        List<Course> courses  =  repo.findAll();
        if(courses == null){
            throw new EntityNotFoundException("No Courses Found.");
        }
        return getAllCourcesMapper.courseToResponse(courses);
    }

    public String updateCourse(Integer id, Course newCourseDetails) {
        Course existingCourse = repo.findById(id).orElseThrow(
            () -> new EntityNotFoundException("Course with id " + id + " not Found.")
            );
            existingCourse.setCourseCode(newCourseDetails.getCourseCode());
            existingCourse.setCourseName(newCourseDetails.getCourseName());
            existingCourse.setCourseDescription(newCourseDetails.getCourseDescription());
            existingCourse.setCredits(newCourseDetails.getCredits());
        repo.save(existingCourse);
        return "Course with id " + id + " has been updated sucessfully.";
    }

    public Course getCourseById(Integer id) {
       return repo.findById(id).orElseThrow(
        () -> new EntityNotFoundException("Course with id " + id + " not found.")
       );
    }

	public String deleteCourse(Integer id) {
        if(repo.existsById(id))
            repo.deleteById(id);
        else 
            throw new EntityNotFoundException("Course with id " + id + " not found.");
        
       return "Course is deleted.";
	}
}
