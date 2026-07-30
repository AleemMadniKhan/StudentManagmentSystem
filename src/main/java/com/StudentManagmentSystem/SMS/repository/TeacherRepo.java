package com.StudentManagmentSystem.SMS.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.StudentManagmentSystem.SMS.model.Teacher;

@Repository
public interface TeacherRepo extends JpaRepository<Teacher, Integer> {
    boolean existsByTeacherId(String teacherId);

    Teacher findByUser_Email(String email);

    List<Teacher> findTop5ByOrderByIdDesc();
}
