package com.m1ndbuzz.employee_test_app.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.m1ndbuzz.employee_test_app.exception.ResourceNotFoundException;
import com.m1ndbuzz.employee_test_app.mapper.TeamMapper;
import com.m1ndbuzz.employee_test_app.model.Employee;
import com.m1ndbuzz.employee_test_app.model.Team;
import com.m1ndbuzz.employee_test_app.model.dto.CreateTeamDTO;
import com.m1ndbuzz.employee_test_app.model.dto.TeamDTO;
import com.m1ndbuzz.employee_test_app.model.dto.UpdateTeamDTO;
import com.m1ndbuzz.employee_test_app.repository.EmployeeRepository;
import com.m1ndbuzz.employee_test_app.repository.TeamRepository;
import com.m1ndbuzz.employee_test_app.service.TeamService;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;
    private final EmployeeRepository employeeRepository;
    private final TeamMapper teamMapper;

    @Override
    public TeamDTO create(CreateTeamDTO dto) {
        Employee lead = null;
        if(dto.getTeamLeadId() != null){
        lead = employeeRepository.findById(dto.getTeamLeadId())
            .orElseThrow(() -> new ResourceNotFoundException("Team lead not found with ID: " + dto.getTeamLeadId()));
        }

        Team team = Team.builder()
            .name(dto.getName())
            .teamLead(lead)
            .build();

        return teamMapper.toDto(teamRepository.save(team));
    }

    @Override
    public TeamDTO get(Long id) {
        Team team = teamRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Team not found with ID: " + id));
        return teamMapper.toDto(team);
    }

    @Override
    public List<TeamDTO> getAll() {
        return teamRepository.findAll()
            .stream()
            .map(teamMapper::toDto)
            .toList();
    }

    @Override
    public TeamDTO update(UpdateTeamDTO dto) {
        Team team = teamRepository.findById(dto.getId())
            .orElseThrow(() -> new ResourceNotFoundException("Team not found with ID: " + dto.getId()));

        if( dto.getTeamLeadId() != null){
            Employee lead = employeeRepository.findById(dto.getTeamLeadId())
                .orElseThrow(() -> new ResourceNotFoundException("Team lead not found with ID: " + dto.getTeamLeadId()));
            team.setTeamLead(lead);
        }
        
        if(dto.getName() != null) {
            team.setName(dto.getName());
        }

        

        return teamMapper.toDto(teamRepository.save(team));
    }

    @Override
    public void delete(Long id) {
        Team team = teamRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Team not found with ID: " + id));
        teamRepository.delete(team);
    }
    
}
