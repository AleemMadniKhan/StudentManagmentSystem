package com.StudentManagmentSystem.SMS.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.StudentManagmentSystem.SMS.model.Section;

@Repository
public interface SectionRepo extends JpaRepository<Section, Integer>{
    
}
