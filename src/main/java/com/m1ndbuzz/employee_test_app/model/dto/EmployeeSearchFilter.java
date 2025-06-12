package com.m1ndbuzz.employee_test_app.model.dto;

import lombok.Data;

@Data
public class EmployeeSearchFilter {
    private String name;
    private String teamName;
    private String teamLeadName;
}
