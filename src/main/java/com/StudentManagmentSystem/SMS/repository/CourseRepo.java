package com.StudentManagmentSystem.SMS.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.StudentManagmentSystem.SMS.model.Course;

@Repository
public interface CourseRepo extends JpaRepository<Course, Integer> {
    boolean existsByCourseCode(String courseCode);

    List<Course> findTop5ByOrderByIdDesc();
}
