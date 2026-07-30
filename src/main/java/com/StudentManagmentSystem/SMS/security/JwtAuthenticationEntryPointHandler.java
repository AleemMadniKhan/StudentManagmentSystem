package com.StudentManagmentSystem.SMS.security;

import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import com.StudentManagmentSystem.SMS.dto.response.ErrorResponseDto;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tools.jackson.databind.ObjectMapper;

public class JwtAuthenticationEntryPointHandler implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException authException) throws IOException, ServletException {
                ErrorResponseDto errorResponse = new ErrorResponseDto();
                errorResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
                errorResponse.setError(HttpStatus.UNAUTHORIZED.getReasonPhrase());
                errorResponse.setMessage("Unauthorize");
                errorResponse.setPath(request.getRequestURI());
                errorResponse.setTimeStamp(LocalDateTime.now());
                
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);

                new ObjectMapper().writeValue(response.getOutputStream(), errorResponse);
    }
    
}
