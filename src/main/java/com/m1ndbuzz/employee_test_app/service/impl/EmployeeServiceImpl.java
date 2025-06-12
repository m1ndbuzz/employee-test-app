package com.m1ndbuzz.employee_test_app.service.impl;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.m1ndbuzz.employee_test_app.exception.BadRequestException;
import com.m1ndbuzz.employee_test_app.exception.ResourceNotFoundException;
import com.m1ndbuzz.employee_test_app.mapper.EmployeeMapper;
import com.m1ndbuzz.employee_test_app.model.Employee;
import com.m1ndbuzz.employee_test_app.model.Team;
import com.m1ndbuzz.employee_test_app.model.dto.CreateEmployeeDTO;
import com.m1ndbuzz.employee_test_app.model.dto.EmployeeDTO;
import com.m1ndbuzz.employee_test_app.model.dto.UpdateEmployeeDTO;
import com.m1ndbuzz.employee_test_app.repository.EmployeeRepository;
import com.m1ndbuzz.employee_test_app.repository.TeamRepository;
import com.m1ndbuzz.employee_test_app.service.EmployeeService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService{

    private final EmployeeRepository employeeRepository;
    private final TeamRepository teamRepository;
    private final EmployeeMapper mapper;

    @Transactional(readOnly = true)
    @Override
    public List<EmployeeDTO> search(String name, String teamName, String teamLeadName, Pageable pageable) {
        return employeeRepository.search(name, teamName, teamLeadName, pageable)
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    @Transactional
    @Override
    public EmployeeDTO create(CreateEmployeeDTO dto) {
        Team team = null;
        if(dto.getTeamName() != null) {
            team = teamRepository.findByName(dto.getTeamName())
                .orElseThrow(() -> new ResourceNotFoundException("Team not found: " + dto.getTeamName()));
        }

        Employee employee = Employee.builder()
                .name(dto.getName())
                .team(team)
                .build();

        return mapper.toDto(employeeRepository.save(employee));
    }

    @Transactional(readOnly = true)
    @Override
    public EmployeeDTO get(Long id) {
        return employeeRepository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id " + id));
    }

    @Transactional
    @Override
    public EmployeeDTO update(Long id, UpdateEmployeeDTO dto) {
        if (!id.equals(dto.getId())) {
            throw new BadRequestException("ID in path and DTO do not match");
        }

        Employee existing = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id " + id));

        if(dto.getName() != null){
            existing.setName(dto.getName());
        }
        
        if (dto.getTeamName() != null) {
            Team team = teamRepository.findByName(dto.getTeamName())
                    .orElseThrow(() -> new ResourceNotFoundException("Team not found: " + dto.getTeamName()));
            existing.setTeam(team);
        }

        Employee updated = employeeRepository.save(existing);
        return mapper.toDto(updated);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        if (!employeeRepository.existsById(id)) {
            throw new ResourceNotFoundException("Employee not found with id " + id);
        }
        employeeRepository.deleteById(id);
    }
}

