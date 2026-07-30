package com.StudentManagmentSystem.SMS.service;
import com.StudentManagmentSystem.SMS.exceptions.BadRequestException;
import com.StudentManagmentSystem.SMS.model.Course;
import com.StudentManagmentSystem.SMS.model.CourseOffering;
import com.StudentManagmentSystem.SMS.model.Enrollment;
import com.StudentManagmentSystem.SMS.model.Student;
import com.StudentManagmentSystem.SMS.model.Marks;
import com.StudentManagmentSystem.SMS.repository.EnrollmentRepo;
import com.StudentManagmentSystem.SMS.repository.MarksRepo;
import com.StudentManagmentSystem.SMS.repository.StudentRepo;


import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GpaService {
    private final StudentRepo studentRepo;
    private final MarksRepo marksRepo;
    private final EnrollmentRepo enrollmentRepo;

    public GpaService(MarksRepo marksRepo, EnrollmentRepo enrollmentRepo, StudentRepo studentRepo){
        this.enrollmentRepo = enrollmentRepo;
        this.marksRepo = marksRepo;
        this.studentRepo = studentRepo;
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

    // public double courseMarks(){}

    public double calculateStudentGpa(Integer studentId){
        Student student = studentRepo.findById(studentId).orElseThrow(
            () -> new BadRequestException("student not found.")
        );
        Map<String, Double> coursesMarks = new HashMap<>();
        double totalQualityPoints = 0.0;
        double courseQualityPoints = 0.0;
        int totalCredits = 0;
        
        List<Enrollment> enrollments = enrollmentRepo.findAllByStudent_studentId(student.getStudentId());
        if (enrollments == null ||enrollments.isEmpty()) {
            return 0.0;
        }

        for (Enrollment enrollment : enrollments) {

            CourseOffering offering = enrollment.getCourseOffering();

            if(offering == null || offering.getCourse() == null) continue;

            Course course = offering.getCourse();

            List<Marks> allMarks = marksRepo.findAllByEnrollment_Id(enrollment.getId());

            if (allMarks.isEmpty()) continue;

            double obtainedWeightage = 0.0;
            double totalWeightage = 0.0;
            double coursePercent = 0.0;

            for (Marks mark : allMarks) {
                if (mark.getObtainedMarks() != null && mark.getTotalMarks() != null && mark.getWeightage() != null) {
                    if(mark.getTotalMarks() <= 0) continue;
                    obtainedWeightage +=  (mark.getObtainedMarks() / mark.getTotalMarks()) * mark.getWeightage();
                    totalWeightage += mark.getWeightage();
                }
            }
            
            if(totalWeightage > 100.0) continue;

            coursePercent = coursePercentage(obtainedWeightage, totalWeightage);
            coursesMarks.put(course.getCourseCode(), coursePercent);
            courseQualityPoints = percentToGpaPoints(coursePercent) * course.getCredits();

            totalCredits += course.getCredits();
            totalQualityPoints += courseQualityPoints;

        }
        return (totalCredits == 0) ? 0.0 : Math.round((totalQualityPoints/totalCredits)*100.0) /100.0;
    }

}
