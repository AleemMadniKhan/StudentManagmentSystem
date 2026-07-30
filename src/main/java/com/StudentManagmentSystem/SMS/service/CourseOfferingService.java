package com.StudentManagmentSystem.SMS.service;

import com.StudentManagmentSystem.SMS.dto.request.AddCourseOfferingRequestDto;
import com.StudentManagmentSystem.SMS.dto.response.CourseOfferingResponseDto;
import com.StudentManagmentSystem.SMS.exceptions.BadRequestException;
import com.StudentManagmentSystem.SMS.mapper.CourseOfferingMapper;
import com.StudentManagmentSystem.SMS.model.Course;
import com.StudentManagmentSystem.SMS.model.CourseOffering;
import com.StudentManagmentSystem.SMS.model.Section;
import com.StudentManagmentSystem.SMS.model.Teacher;
import com.StudentManagmentSystem.SMS.repository.CourseRepo;
import com.StudentManagmentSystem.SMS.repository.CourseOfferingRepo;
import com.StudentManagmentSystem.SMS.repository.SectionRepo;
import com.StudentManagmentSystem.SMS.repository.TeacherRepo;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import java.util.List;

@Service
public class CourseOfferingService {

    private final CourseOfferingRepo courseOfferingRepo;
    private final CourseRepo courseRepo;
    private final SectionRepo sectionRepo;
    private final TeacherRepo teacherRepo;

    public CourseOfferingService(CourseOfferingRepo courseOfferingRepo, CourseRepo courseRepo, 
            SectionRepo sectionRepo, TeacherRepo teacherRepo) {
        this.courseOfferingRepo = courseOfferingRepo;
        this.courseRepo = courseRepo;
        this.sectionRepo = sectionRepo;
        this.teacherRepo = teacherRepo;
    }

    @Transactional
    public String addCourseOffering(AddCourseOfferingRequestDto request) {
        if (courseOfferingRepo.existsBySection_IdAndCourse_Id(request.getSectionId(), request.getCourseId())) {
            throw new BadRequestException("This course is already offered in this section.");
        }

        Course course = courseRepo.findById(request.getCourseId())
                .orElseThrow(() -> new BadRequestException("Course not found."));

        Section section = sectionRepo.findById(request.getSectionId())
                .orElseThrow(() -> new BadRequestException("Section not found."));

        Teacher teacher = teacherRepo.findById(request.getTeacherId())
                .orElseThrow(() -> new BadRequestException("Teacher not found."));

        CourseOffering offering = CourseOfferingMapper.requestToEntity(course, section, teacher);
        courseOfferingRepo.save(offering);

        return "Course '" + course.getCourseName() + "' assigned to Section '" + section.getName() + "' under Instructor ID: " + teacher.getTeacherId()+"/ " + teacher.getUser().getUsername();
    }
    public List<CourseOfferingResponseDto> getCoursesBySection(Integer sectionId) {
        List<CourseOffering> offerings = courseOfferingRepo.findAllBySection_Id(sectionId);
        return CourseOfferingMapper.entityToResponseList(offerings);
    }
        public List<CourseOfferingResponseDto> getAllCourseOffering() {
        List<CourseOffering> offerings = courseOfferingRepo.findAll();
        return CourseOfferingMapper.entityToResponseList(offerings);
    }
}
