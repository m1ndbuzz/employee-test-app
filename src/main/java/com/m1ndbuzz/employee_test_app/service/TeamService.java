package com.m1ndbuzz.employee_test_app.service;

import java.util.List;

import com.m1ndbuzz.employee_test_app.model.dto.CreateTeamDTO;
import com.m1ndbuzz.employee_test_app.model.dto.TeamDTO;
import com.m1ndbuzz.employee_test_app.model.dto.UpdateTeamDTO;

public interface TeamService {

    TeamDTO create(CreateTeamDTO dto);
    TeamDTO get(Long id);
    List<TeamDTO> getAll();
    TeamDTO update(UpdateTeamDTO dto);
    void delete(Long id);
}