package com.StudentManagmentSystem.SMS.mapper;

import java.util.List;

import com.StudentManagmentSystem.SMS.dto.response.studentDashboardResponse.StudentAcademicsResponseDto;
import com.StudentManagmentSystem.SMS.dto.response.studentDashboardResponse.StudentDashboardResponseDto;
import com.StudentManagmentSystem.SMS.model.Enrollment;
import com.StudentManagmentSystem.SMS.model.Student;

public class StudentDashboardMapper {

    public static StudentDashboardResponseDto studentDashboardResponse(Student student, List<StudentAcademicsResponseDto> courses,
            Double gpa, List<Enrollment> enrollments) {
        StudentDashboardResponseDto studentDashboard = new StudentDashboardResponseDto();
        studentDashboard.setStudentId(student.getStudentId());
        studentDashboard.setStudentName(student.getUser().getUsername());
        studentDashboard.setRollNumber(student.getRollNumber());
        studentDashboard.setEmail(student.getUser().getEmail());
        studentDashboard.setRegisteredCourses(enrollments.size());

        if (!enrollments.isEmpty()) {
            studentDashboard.setSemesterId(
                    enrollments.get(0)
                            .getCourseOffering()
                            .getSection()
                            .getSemester()
                            .getId());

            studentDashboard.setSemesterName(
                    enrollments.get(0)
                            .getCourseOffering()
                            .getSection()
                            .getSemester()
                            .getSemesterName());
        }
        studentDashboard.setCourses(courses);
        int totalCredits = 0;
        for (StudentAcademicsResponseDto course : courses) {
            if (course != null) {
                totalCredits += course.getCredits();
            }
        }
        studentDashboard.setCompletedCredits(totalCredits);
        studentDashboard.setGpa(gpa);
        return studentDashboard;
    }
}
