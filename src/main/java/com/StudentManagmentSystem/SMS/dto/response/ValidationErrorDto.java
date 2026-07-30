package com.StudentManagmentSystem.SMS.dto.response;

import java.time.LocalDateTime;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValidationErrorDto {
    
    private LocalDateTime timeStamp;
    private int status;
    private String error;
    private String message;
    private String path;
    private Map<String, String> validationsError;

}
