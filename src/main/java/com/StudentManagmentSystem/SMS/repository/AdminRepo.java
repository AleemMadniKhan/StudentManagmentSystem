package com.StudentManagmentSystem.SMS.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.StudentManagmentSystem.SMS.model.Admin;

public interface AdminRepo extends JpaRepository<Admin, Integer>{

    Admin findByUser_Email(String email);

}
