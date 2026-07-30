package com.StudentManagmentSystem.SMS.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.StudentManagmentSystem.SMS.exceptions.BadRequestException;
import com.StudentManagmentSystem.SMS.model.RefreshToken;
import com.StudentManagmentSystem.SMS.model.User;
import com.StudentManagmentSystem.SMS.repository.RefreshTokenRepo;

@Service
public class RefreshTokenService {

    private final RefreshTokenRepo repo;
    public RefreshTokenService(RefreshTokenRepo repo){
        this.repo = repo;
    }
    
    public RefreshToken generatRefreshToken(User user){
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setRefreshToken(UUID.randomUUID().toString());
        refreshToken.setUser(user);
        refreshToken.setCreatedAt(LocalDateTime.now());
        refreshToken.setExpiredAt(LocalDateTime.now().plusDays(7));
        repo.save(refreshToken);
        return refreshToken;
    }

    public RefreshToken varifyRefreshToken(String token){
       RefreshToken refreshToken = repo.findByRefreshToken(token);

       if (refreshToken == null) {
        throw new BadRequestException("Refresh Token not found");
       }

       if (refreshToken.getExpiredAt().isBefore(LocalDateTime.now())) {
        throw new BadRequestException("The refresh Token is expired");
       }
       
        return refreshToken;
    }
}
