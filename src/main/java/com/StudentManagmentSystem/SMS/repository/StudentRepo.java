package com.StudentManagmentSystem.SMS.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.StudentManagmentSystem.SMS.model.Student;

@Repository
public interface StudentRepo extends JpaRepository<Student, Integer> {
    boolean existsByRollNumber(String rollNumber);

    Student findByUser_Email(String email);

    List<Student> findTop5ByOrderByStudentIdDesc();
}
