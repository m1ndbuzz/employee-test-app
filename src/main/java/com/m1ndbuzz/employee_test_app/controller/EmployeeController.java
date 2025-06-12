package com.m1ndbuzz.employee_test_app.controller;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.m1ndbuzz.employee_test_app.model.dto.CreateEmployeeDTO;
import com.m1ndbuzz.employee_test_app.model.dto.EmployeeDTO;
import com.m1ndbuzz.employee_test_app.model.dto.UpdateEmployeeDTO;
import com.m1ndbuzz.employee_test_app.service.EmployeeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
@Tag(name = "Employee API", description = "API for managing employees")
public class EmployeeController {

    private final EmployeeService employeeService;
    
    @GetMapping("/search")
    @Operation(summary = "Search employees by name, team name, and team lead ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "List of matching employees retrieved successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid search parameters")
    })
    public ResponseEntity<List<EmployeeDTO>> search(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String teamName,
            @RequestParam(required = false) String teamLeadName,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        List<EmployeeDTO> result = employeeService.search(name, teamName, teamLeadName, pageable);
        return ResponseEntity.ok(result);
    }

    @PostMapping
    @Operation(summary = "Create a new employee")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Employee created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "Team not found")
    })
    public ResponseEntity<EmployeeDTO> create(@Valid @RequestBody CreateEmployeeDTO dto) {
        EmployeeDTO created = employeeService.create(dto);
        return ResponseEntity.status(201).body(created);
    }

    @Operation(summary = "Get an employee by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Employee retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Employee not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> get(@PathVariable Long id) {
        return ResponseEntity.ok(employeeService.get(id));
    }

    @Operation(summary = "Update an employee")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Employee updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "Employee or team not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody UpdateEmployeeDTO dto
    ) {
        return ResponseEntity.ok(employeeService.update(id, dto));
    }

    @Operation(summary = "Delete an employee")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Employee deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Employee not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        employeeService.delete(id);
        return ResponseEntity.status(200).build();
    }
    
}

