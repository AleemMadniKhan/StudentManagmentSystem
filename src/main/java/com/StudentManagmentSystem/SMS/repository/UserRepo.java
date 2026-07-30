package com.StudentManagmentSystem.SMS.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.StudentManagmentSystem.SMS.model.User;
import java.util.Optional;


@Repository
public interface UserRepo extends JpaRepository<User, Integer>{
    User findByEmail(String email);
    User findByUsername(String username);
    boolean existsByEmail(String email);
    Optional<User> findById(Integer id);
}
