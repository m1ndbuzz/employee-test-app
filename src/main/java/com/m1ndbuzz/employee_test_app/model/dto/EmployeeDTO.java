package com.m1ndbuzz.employee_test_app.model.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class EmployeeDTO {

    private Long id;

    private String name;

    private String teamName;

    private String teamLeadName;
}
