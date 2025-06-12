package com.m1ndbuzz.employee_test_app.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.m1ndbuzz.employee_test_app.model.Employee;
import com.m1ndbuzz.employee_test_app.model.dto.EmployeeDTO;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EmployeeMapper {

    @Mapping(source = "team.name", target = "teamName")
    @Mapping(source = "team.teamLead.name", target = "teamLeadName")
    EmployeeDTO toDto(Employee employee);

}
