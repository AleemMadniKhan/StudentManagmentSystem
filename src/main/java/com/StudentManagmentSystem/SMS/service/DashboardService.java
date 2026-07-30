package com.StudentManagmentSystem.SMS.service;

import com.StudentManagmentSystem.SMS.dto.response.adminDashboardResponse.AdminDashBoardResponseDto;
import com.StudentManagmentSystem.SMS.dto.response.adminDashboardResponse.AdminStatisticsResponseDto;
import com.StudentManagmentSystem.SMS.dto.response.adminDashboardResponse.RecentCoursesResponseDto;
import com.StudentManagmentSystem.SMS.dto.response.studentDashboardResponse.StudentAcademicsResponseDto;
import com.StudentManagmentSystem.SMS.dto.response.studentDashboardResponse.StudentDashboardResponseDto;
import com.StudentManagmentSystem.SMS.dto.response.teacherDashboardResponse.TeacherAssingedCoursesResponseDto;
import com.StudentManagmentSystem.SMS.dto.response.teacherDashboardResponse.TeacherDashboardResponseDto;
import com.StudentManagmentSystem.SMS.exceptions.EntityNotFoundException;
import com.StudentManagmentSystem.SMS.mapper.AdminDashboardMapper;
import com.StudentManagmentSystem.SMS.mapper.StudentDashboardMapper;
import com.StudentManagmentSystem.SMS.mapper.TeacherDashboardMappper;
import com.StudentManagmentSystem.SMS.model.Course;
import com.StudentManagmentSystem.SMS.model.CourseOffering;
import com.StudentManagmentSystem.SMS.model.Enrollment;
import com.StudentManagmentSystem.SMS.model.Section;
import com.StudentManagmentSystem.SMS.model.Student;
import com.StudentManagmentSystem.SMS.model.Teacher;
import com.StudentManagmentSystem.SMS.model.User;
import com.StudentManagmentSystem.SMS.repository.CourseOfferingRepo;
import com.StudentManagmentSystem.SMS.repository.CourseRepo;
import com.StudentManagmentSystem.SMS.repository.EnrollmentRepo;
import com.StudentManagmentSystem.SMS.repository.SectionRepo;
import com.StudentManagmentSystem.SMS.repository.StudentRepo;
import com.StudentManagmentSystem.SMS.repository.TeacherRepo;
import com.StudentManagmentSystem.SMS.repository.UserRepo;
import com.StudentManagmentSystem.SMS.dto.response.adminDashboardResponse.RecentStudentsResponseDto;
import com.StudentManagmentSystem.SMS.dto.response.adminDashboardResponse.RecentTeachersResponseDto;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class DashboardService {
    private final EnrollmentRepo enrollmentRepo;
    private final StudentRepo studentRepo;
    private final GpaService gpaService;
    private final StudentAcademicService courseMarksService;
    private final TeacherRepo teacherRepo;
    private final CourseOfferingRepo courseOfferingRepo;
    private final UserRepo userRepo;
    private final CourseRepo courseRepo;
    private final SectionRepo sectionRepo;

    public DashboardService(GpaService gpaService, StudentRepo studentRepo, EnrollmentRepo enrollmentRepo,
       CourseOfferingRepo courseOfferingRepo, UserRepo userRepo, TeacherRepo teacherRepo, 
       CourseRepo courseRepo, SectionRepo sectionRepo, StudentAcademicService courseMarksService)
       
       {
        this.gpaService = gpaService;
        this.studentRepo = studentRepo;
        this.enrollmentRepo = enrollmentRepo;
        this.courseMarksService  =courseMarksService;
        this.teacherRepo = teacherRepo;
        this.courseOfferingRepo = courseOfferingRepo;
        this.userRepo = userRepo;
        this.courseRepo = courseRepo;
        this.sectionRepo= sectionRepo;
    }

    public StudentDashboardResponseDto getStudentDashboard(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User is not authenticated");
        }
        String email = authentication.getName();
        Student student = studentRepo.findByUser_Email(email);


        List<StudentAcademicsResponseDto> courses = courseMarksService.getCoursesMarks(student.getStudentId());
        
        Double gpa = gpaService.calculateStudentGpa(student.getStudentId());
        List<Enrollment> enrollments = enrollmentRepo.findAllByStudent_studentId(student.getStudentId());
        StudentDashboardResponseDto studentDashboard = 
        StudentDashboardMapper.studentDashboardResponse(student, courses, gpa, enrollments);

        return studentDashboard;
    }   


    public TeacherDashboardResponseDto getTeacherDashboard(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User is not authenticated");
        }
        String email = authentication.getName();
        Teacher teacher = teacherRepo.findByUser_Email(email);

        if(teacher == null){
            throw new EntityNotFoundException("No teacher found.");
        }

        List<CourseOffering> offerings = courseOfferingRepo.findAllByTeacher_Id(teacher.getId());

        List<Enrollment> allEnrollments = new ArrayList<>();
        List<Course> courses = new ArrayList<>();
        List<Section> sections = new ArrayList<>();
       List<TeacherAssingedCoursesResponseDto> assignedCourses = new ArrayList<>();
       for (CourseOffering offering : offerings) {
            List<Enrollment> enrollments = enrollmentRepo.findAllByCourseOfferingId(offering.getId());
            TeacherAssingedCoursesResponseDto assignedCourse = TeacherDashboardMappper.TeacherAssingedCoursesResponseDto(offering, enrollments);
            assignedCourses.add(assignedCourse);
            courses.add(offering.getCourse());
            sections.add(offering.getSection());
            allEnrollments.addAll(enrollments);
        }
        
        TeacherDashboardResponseDto teacherDashboard = 
        TeacherDashboardMappper.teacherDashboardMapper(teacher, offerings,assignedCourses,
         allEnrollments, new LinkedHashSet<>(courses).size(), new LinkedHashSet<>(sections).size());
        return teacherDashboard;
    }
    
    public AdminDashBoardResponseDto adminDashboard(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!authentication.isAuthenticated()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "user is not authenticated.");
        }
        String email = authentication.getName();
        User user = userRepo.findByEmail(email);
        
        List<Course> recentCourses = courseRepo.findTop5ByOrderByIdDesc();
        List<Teacher> recentTeachers = teacherRepo.findTop5ByOrderByIdDesc();
        List<Student> recentStudents = studentRepo.findTop5ByOrderByStudentIdDesc();
    
        List<RecentCoursesResponseDto> recentCoursesResponseDto = AdminDashboardMapper.recentCoursesMapper(recentCourses);
        List<RecentTeachersResponseDto> recentTeachersResponseDto = AdminDashboardMapper.recentTeachersMapper(recentTeachers);
        List<RecentStudentsResponseDto> recentStudentsResponseDto = AdminDashboardMapper.recentStudentsMapper(recentStudents); 

        AdminStatisticsResponseDto statistics = AdminDashboardMapper.statisticsMapper(
        courseRepo.count(),
        sectionRepo.count(), teacherRepo.count(), 
        studentRepo.count(), enrollmentRepo.count(),
        courseOfferingRepo.count()
       );      
       
        AdminDashBoardResponseDto adminDashboardReponse = 
        AdminDashboardMapper.adminDashboardResponse(user, statistics, recentStudentsResponseDto,
        recentTeachersResponseDto,recentCoursesResponseDto);
        return adminDashboardReponse;
    }
    
}
