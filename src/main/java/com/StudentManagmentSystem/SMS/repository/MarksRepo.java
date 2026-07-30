package com.StudentManagmentSystem.SMS.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.StudentManagmentSystem.SMS.model.Marks;

@Repository
public interface MarksRepo extends JpaRepository<Marks, Integer>{

    List<Marks> findAllByEnrollment_Id(Integer id);
    
}