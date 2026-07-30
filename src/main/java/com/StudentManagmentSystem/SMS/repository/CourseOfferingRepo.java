package com.StudentManagmentSystem.SMS.repository;

import com.StudentManagmentSystem.SMS.model.CourseOffering;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CourseOfferingRepo extends JpaRepository<CourseOffering, Integer> {
    
    List<CourseOffering> findAllBySection_Id(Integer sectionId);

    boolean existsBySection_IdAndCourse_Id(Integer sectionId, Integer courseId);

    List<CourseOffering> findAllByTeacher_Id(Integer id);
}
