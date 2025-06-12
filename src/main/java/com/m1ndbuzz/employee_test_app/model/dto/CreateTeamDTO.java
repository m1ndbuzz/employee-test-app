package com.m1ndbuzz.employee_test_app.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateTeamDTO {
    @NotBlank(message = "Team name is required")
    private String name;

    private Long teamLeadId;
}

