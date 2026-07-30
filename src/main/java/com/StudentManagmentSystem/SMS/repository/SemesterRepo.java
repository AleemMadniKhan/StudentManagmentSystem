package com.StudentManagmentSystem.SMS.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.StudentManagmentSystem.SMS.model.Semester;

@Repository
public interface SemesterRepo extends JpaRepository<Semester, Integer>{
}
