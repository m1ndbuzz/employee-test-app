package com.m1ndbuzz.employee_test_app.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.m1ndbuzz.employee_test_app.model.Team;
import com.m1ndbuzz.employee_test_app.model.dto.CreateTeamDTO;
import com.m1ndbuzz.employee_test_app.model.dto.TeamDTO;
import com.m1ndbuzz.employee_test_app.model.dto.UpdateTeamDTO;

@Mapper(componentModel = "spring")
public interface TeamMapper {
    @Mapping(source = "teamLead.name", target = "teamLeadName")
    TeamDTO toDto(Team entity);
}
