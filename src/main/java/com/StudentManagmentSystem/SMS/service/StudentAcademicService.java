package com.StudentManagmentSystem.SMS.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.StudentManagmentSystem.SMS.dto.response.CourseAssessmentMarksResponseDto;
import com.StudentManagmentSystem.SMS.dto.response.studentDashboardResponse.StudentAcademicsResponseDto;
import com.StudentManagmentSystem.SMS.exceptions.BadRequestException;
import com.StudentManagmentSystem.SMS.model.Course;
import com.StudentManagmentSystem.SMS.model.CourseOffering;
import com.StudentManagmentSystem.SMS.model.Enrollment;
import com.StudentManagmentSystem.SMS.model.Marks;
import com.StudentManagmentSystem.SMS.model.Student;
import com.StudentManagmentSystem.SMS.repository.EnrollmentRepo;
import com.StudentManagmentSystem.SMS.repository.MarksRepo;
import com.StudentManagmentSystem.SMS.repository.StudentRepo;

@Service
public class StudentAcademicService {

    private final StudentRepo studentRepo;
    private final MarksRepo marksRepo;
    private final EnrollmentRepo enrollmentRepo;
    private final CourseAssessmentMarksService courseAssessmentMarksService;
    public StudentAcademicService(MarksRepo marksRepo,
    EnrollmentRepo enrollmentRepo,
    StudentRepo studentRepo,
    CourseAssessmentMarksService courseAssessmentMarksService){
        this.enrollmentRepo = enrollmentRepo;
        this.marksRepo = marksRepo;
        this.studentRepo = studentRepo;
        this.courseAssessmentMarksService = courseAssessmentMarksService;
    }

    public double coursePercentage(double obtained, double total){
        if (obtained < 0 || total <= 0) {
            return 0.0;
        }
        double coursePercentMarks = (obtained / total) * 100;
        return coursePercentMarks;
    }

    public double percentToGpaPoints(double percent){
        if(percent >= 91) return 4.0;
        if(percent >= 80) return 3.66;
        if(percent >= 75) return 3.33;
        if(percent >= 71) return 3.0;
        if(percent >= 68) return 2.66;
        if(percent >= 65) return 2.33;
        if(percent >= 61) return 2.0;
        if(percent >= 58) return 1.66;
        if(percent >= 55) return 1.33;
        if(percent >= 50) return 1.0;
        return 0.0;
    }

    public List<StudentAcademicsResponseDto> getCoursesMarks(Integer studentId){
        Student student = studentRepo.findById(studentId).orElseThrow(
            () -> new BadRequestException("student not found.")
        );

        List<Enrollment> enrollments = enrollmentRepo.findAllByStudent_studentId(student.getStudentId());
        if (enrollments == null ||enrollments.isEmpty()) {
            return Collections.emptyList();
        }

        List<StudentAcademicsResponseDto> courses = new ArrayList<>(); 

        for (Enrollment enrollment : enrollments) {
            CourseOffering offering = enrollment.getCourseOffering();
            if(offering == null || offering.getCourse() == null) continue;
            Course course = offering.getCourse();
            List<Marks> allMarks = marksRepo.findAllByEnrollment_Id(enrollment.getId());
            if (allMarks.isEmpty()) continue;
            double obtainedWeightage = 0.0;
            double totalWeightage = 0.0;
            double coursePercent = 0.0;
            StudentAcademicsResponseDto courseDashboard = new StudentAcademicsResponseDto();
            List<CourseAssessmentMarksResponseDto> courseAssemesssmentsMarks = 
                    courseAssessmentMarksService.getAssemssmentMarks(enrollment.getId());
            for (Marks mark : allMarks) {
                if (mark.getObtainedMarks() != null && mark.getTotalMarks() != null && mark.getWeightage() != null) {
                    if(mark.getTotalMarks() <= 0) continue;
                    obtainedWeightage +=  (mark.getObtainedMarks() / mark.getTotalMarks()) * mark.getWeightage();
                    totalWeightage += mark.getWeightage();
                     courseDashboard.setAssessmentMarks(courseAssemesssmentsMarks);
                }
            }

            coursePercent = coursePercentage(obtainedWeightage, totalWeightage);
            courseDashboard.setCourseCode(course.getCourseCode());
            courseDashboard.setCourseName(course.getCourseName());
            courseDashboard.setCredits(course.getCredits());
            courseDashboard.setGradePoint(percentToGpaPoints(coursePercent));
            courseDashboard.setInstructorName(enrollment.getCourseOffering().getTeacher().getUser().getUsername());
            courseDashboard.setCoursePercentage(obtainedWeightage);

            courses.add(courseDashboard);
        }
      return courses;
    }
}