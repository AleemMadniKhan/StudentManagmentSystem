package com.StudentManagmentSystem.SMS.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.StudentManagmentSystem.SMS.model.RefreshToken;

@Repository
public interface RefreshTokenRepo extends JpaRepository<RefreshToken, Integer> {

    RefreshToken findByRefreshToken(String refreshToken);

}
