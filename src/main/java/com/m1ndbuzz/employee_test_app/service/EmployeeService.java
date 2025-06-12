package com.m1ndbuzz.employee_test_app.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.m1ndbuzz.employee_test_app.model.dto.CreateEmployeeDTO;
import com.m1ndbuzz.employee_test_app.model.dto.EmployeeDTO;
import com.m1ndbuzz.employee_test_app.model.dto.UpdateEmployeeDTO;

public interface EmployeeService {
    List<EmployeeDTO> search(String name, String teamName, String teamLeadName, Pageable pageable);
    EmployeeDTO create(CreateEmployeeDTO dto);
    EmployeeDTO get(Long id);
    EmployeeDTO update(Long id, UpdateEmployeeDTO dto);
    void delete(Long id);
}
