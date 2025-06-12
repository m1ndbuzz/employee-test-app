package com.m1ndbuzz.employee_test_app.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.m1ndbuzz.employee_test_app.model.dto.CreateTeamDTO;
import com.m1ndbuzz.employee_test_app.model.dto.TeamDTO;
import com.m1ndbuzz.employee_test_app.model.dto.UpdateTeamDTO;
import com.m1ndbuzz.employee_test_app.service.TeamService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/teams")
@RequiredArgsConstructor
public class TeamController {

    private final TeamService teamService;

    @PostMapping
    public ResponseEntity<TeamDTO> create(@Valid @RequestBody CreateTeamDTO dto) {
        TeamDTO created = teamService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeamDTO> get(@PathVariable Long id) {
        return ResponseEntity.ok(teamService.get(id));
    }

    @GetMapping
    public ResponseEntity<List<TeamDTO>> getAll() {
        return ResponseEntity.ok(teamService.getAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<TeamDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody UpdateTeamDTO dto
        ) {
        if (!id.equals(dto.getId())) {
            throw new IllegalArgumentException("Path ID and DTO ID must match.");
        }
        return ResponseEntity.ok(teamService.update(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        teamService.delete(id);
        return ResponseEntity.status(200).build();
    }
}

