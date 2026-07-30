package com.StudentManagmentSystem.SMS.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddMarksRequestDto {

    @NotNull(message = "Total marks should not be null.")
    @Size(min = 1, max = 100, message = "Total marks should between 1 and 100")
    private Double totalMarks;

    @NotNull(message = "Obtained marks should not be null.")
    private Double obtainedMarks;

    @NotBlank(message = "Assessment type should not be blank.")
    private String assessmentType;
    
    @NotNull(message = "Enrollment id should not be null.")
    private Integer enrollmentId;

    @NotNull(message = "Marks weightage should not be null.")
    private Double weightage;
}
