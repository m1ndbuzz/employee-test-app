package com.m1ndbuzz.employee_test_app.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpdateEmployeeDTO {

    @NotNull(message = "Id is required for update")
    private Long id;

    private String name;

    private String teamName;
    
}
