package com.StudentManagmentSystem.SMS.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.StudentManagmentSystem.SMS.model.Enrollment;

@Repository
public interface EnrollmentRepo extends JpaRepository<Enrollment, Integer>{


    List<Enrollment> findAllByStudent_studentId(Integer studentId);


    boolean existsByStudent_studentIdAndCourseOffering_id(Integer studentId, Integer courseOfferingId);


    List<Enrollment> findByCourseOffering_Teacher_IdAndCourseOffering_Course_Id(Integer id, Integer id2);


    List<Enrollment> findByCourseOfferingId(Integer id);


    List<Enrollment> findAllByCourseOfferingId(Integer id);
    
}
