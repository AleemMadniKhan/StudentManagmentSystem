package com.StudentManagmentSystem.SMS.mapper;

import java.util.ArrayList;
import java.util.List;

import com.StudentManagmentSystem.SMS.dto.response.teacherDashboardResponse.TeacherAssingedCoursesResponseDto;
import com.StudentManagmentSystem.SMS.dto.response.teacherDashboardResponse.TeacherDashboardEnrollmentDto;
import com.StudentManagmentSystem.SMS.dto.response.teacherDashboardResponse.TeacherDashboardResponseDto;
import com.StudentManagmentSystem.SMS.model.CourseOffering;
import com.StudentManagmentSystem.SMS.model.Enrollment;
import com.StudentManagmentSystem.SMS.model.Teacher;

public class TeacherDashboardMappper {
    
    public static TeacherDashboardResponseDto teacherDashboardMapper(Teacher teacher,List<CourseOffering> offerings,List<TeacherAssingedCoursesResponseDto> teacherAssingedCourses, List<Enrollment> enrollments, 
    Integer numberOfAssignedCourses, Integer numberOfSections){
        TeacherDashboardResponseDto teacherDashboardReponse = new TeacherDashboardResponseDto();
        teacherDashboardReponse.setTeacherId(teacher.getId());
        teacherDashboardReponse.setEmployeeId(teacher.getTeacherId());
        teacherDashboardReponse.setTeacherName(teacher.getUser().getUsername());
        teacherDashboardReponse.setDepartment(teacher.getDepartment());
        teacherDashboardReponse.setEmail(teacher.getUser().getEmail());
        teacherDashboardReponse.setHireDate(teacher.getHireDate());
        teacherDashboardReponse.setNumberOfAssignedCourses(numberOfAssignedCourses);
        teacherDashboardReponse.setNumberOfAssignedSections(numberOfSections);
        teacherDashboardReponse.setAssignedCoursesDetails(teacherAssingedCourses);
        List<TeacherDashboardEnrollmentDto> studentEnrolledResponses = new ArrayList<>();
        enrollments.forEach(enrollment ->{
            TeacherDashboardEnrollmentDto studentEnrolledResponse = new TeacherDashboardEnrollmentDto();
            studentEnrolledResponse.setEnrollmentId(enrollment.getId());
            studentEnrolledResponse.setCourseCode(enrollment.getCourseOffering().getCourse().getCourseCode());
            studentEnrolledResponse.setStudentRollNumber(enrollment.getStudent().getRollNumber());
            studentEnrolledResponse.setStudentName(enrollment.getStudent().getUser().getUsername());
            studentEnrolledResponse.setStudentEmail(enrollment.getStudent().getUser().getEmail());
            studentEnrolledResponse.setSectionName(enrollment.getCourseOffering().getSection().getName());
           studentEnrolledResponses.add(studentEnrolledResponse);
        });
        
        teacherDashboardReponse.setEnrollments(studentEnrolledResponses);
        return teacherDashboardReponse;
    }

    public static TeacherAssingedCoursesResponseDto TeacherAssingedCoursesResponseDto(CourseOffering offering, List<Enrollment> enrollments){
            TeacherAssingedCoursesResponseDto teacherAssingedCourse = new TeacherAssingedCoursesResponseDto();
            teacherAssingedCourse.setCourseCode(offering.getCourse().getCourseCode());
            teacherAssingedCourse.setCourseId(offering.getCourse().getId());
            teacherAssingedCourse.setCourseName(offering.getCourse().getCourseName());
            teacherAssingedCourse.setCredits(offering.getCourse().getCredits());
            teacherAssingedCourse.setSectionName(offering.getSection().getName());
            teacherAssingedCourse.setSemesterName(offering.getSection().getSemester().getSemesterName());
            teacherAssingedCourse.setEnrolledStudents(enrollments.size());
        return teacherAssingedCourse;
    }

}
