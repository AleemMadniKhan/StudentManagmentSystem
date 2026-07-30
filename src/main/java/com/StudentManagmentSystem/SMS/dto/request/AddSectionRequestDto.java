package com.StudentManagmentSystem.SMS.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddSectionRequestDto {
    
    private String name;
    
    @NotNull(message = "Number of seats should not be null.")
    @Min(value = 1, message = "Value must be at least 1")
    @Max(value = 100, message = "Value cannot be greater than 100")
    private Long numberOfSeats;

    @NotNull(message = "Semester id should not be null.")
    private Integer semesterId;

}
