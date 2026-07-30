package com.StudentManagmentSystem.SMS.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class SectionResponseDto {
    private Integer id;
    
    private String name;
    
    private Long numberOfSeats;

    private Integer semesterId;

    private String semesterName;

}
