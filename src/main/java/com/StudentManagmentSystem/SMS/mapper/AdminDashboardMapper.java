package com.StudentManagmentSystem.SMS.mapper;

import java.util.ArrayList;
import java.util.List;

import com.StudentManagmentSystem.SMS.dto.response.adminDashboardResponse.AdminDashBoardResponseDto;
import com.StudentManagmentSystem.SMS.dto.response.adminDashboardResponse.AdminStatisticsResponseDto;
import com.StudentManagmentSystem.SMS.dto.response.adminDashboardResponse.RecentCoursesResponseDto;
import com.StudentManagmentSystem.SMS.dto.response.adminDashboardResponse.RecentStudentsResponseDto;
import com.StudentManagmentSystem.SMS.dto.response.adminDashboardResponse.RecentTeachersResponseDto;
import com.StudentManagmentSystem.SMS.model.Course;
import com.StudentManagmentSystem.SMS.model.Student;
import com.StudentManagmentSystem.SMS.model.Teacher;
import com.StudentManagmentSystem.SMS.model.User;

public class AdminDashboardMapper {
    public static AdminDashBoardResponseDto adminDashboardResponse(
        User user,
        AdminStatisticsResponseDto statistics,
        List<RecentStudentsResponseDto> recentStudentsResponseDto,
        List<RecentTeachersResponseDto> recentTeachersResponseDto,
        List<RecentCoursesResponseDto> recentCoursesResponseDto
    ){
        AdminDashBoardResponseDto dashboardReponse = new AdminDashBoardResponseDto();
        dashboardReponse.setId(user.getId());
        dashboardReponse.setUsername(user.getUsername());
        dashboardReponse.setEmail(user.getEmail());
        dashboardReponse.setStatistics(statistics);
        dashboardReponse.setRecentStudents(recentStudentsResponseDto);
        dashboardReponse.setRecentCourses(recentCoursesResponseDto);
        dashboardReponse.setRecentTeachers(recentTeachersResponseDto);
        return dashboardReponse;
    }

    public static AdminStatisticsResponseDto statisticsMapper(Long totalCourses, 
    Long totalSections, Long toatalTeachers, 
    Long totalStudents, Long totalEnrollments, Long totalCourseOfferings){
        AdminStatisticsResponseDto response = new AdminStatisticsResponseDto();
        response.setTotalCourseOfferings(totalCourseOfferings);
        response.setTotalCourses(totalCourses);
        response.setTotalEnrollments(totalEnrollments);
        response.setTotalSections(totalSections);
        response.setTotalStudents(totalStudents);
        response.setTotalTeachers(toatalTeachers);
        return response;
    }

    public static List<RecentCoursesResponseDto> recentCoursesMapper(List<Course> courses){
        List<RecentCoursesResponseDto> responses = new ArrayList<>();
        courses.forEach(course -> {
            RecentCoursesResponseDto response = new RecentCoursesResponseDto();
            response.setCourseId(course.getId());
            response.setCourseCode(course.getCourseCode());
            response.setCourseName(course.getCourseName());
            response.setCredits(course.getCredits());
            responses.add(response);
        });
        return responses;
    }

    public static List<RecentTeachersResponseDto> recentTeachersMapper(List<Teacher> teachers){
        List<RecentTeachersResponseDto> responses = new ArrayList<>();
        teachers.forEach(teacher -> {
            RecentTeachersResponseDto response = new RecentTeachersResponseDto();
            response.setTeacherId(teacher.getId());
            response.setEmployeeId(teacher.getTeacherId());
            response.setTeacherName(teacher.getUser().getUsername());
            response.setDepartment(teacher.getDepartment());
            responses.add(response);
        });
        return responses;
    }

    public static List<RecentStudentsResponseDto> recentStudentsMapper(List<Student> students){
        List<RecentStudentsResponseDto> responses = new ArrayList<>();
        students.forEach(student -> {
            RecentStudentsResponseDto response = new RecentStudentsResponseDto();
            response.setStudentId(student.getStudentId());
            response.setRollNumber(student.getRollNumber());
            response.setStudentName(student.getUser().getUsername());
            response.setEnrollmentDate(student.getEnrollmentDate());
            responses.add(response);
        });
        return responses;
    }
}
