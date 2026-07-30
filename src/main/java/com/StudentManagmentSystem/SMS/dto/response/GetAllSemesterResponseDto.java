package com.StudentManagmentSystem.SMS.dto.response;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllSemesterResponseDto {
    
    private Integer id;

    private String semesterName;

    private LocalDate startDate;
    
    private LocalDate endDate;
}
