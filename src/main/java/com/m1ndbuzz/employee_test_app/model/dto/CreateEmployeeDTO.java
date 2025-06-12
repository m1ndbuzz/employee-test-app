package com.m1ndbuzz.employee_test_app.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateEmployeeDTO {

    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotBlank(message = "Team name is mandatory")
    private String teamName;
}