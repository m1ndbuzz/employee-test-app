package com.m1ndbuzz.employee_test_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.m1ndbuzz.employee_test_app.model.Team;

import java.util.Optional;

public interface TeamRepository extends JpaRepository<Team, Long> {
    Optional<Team> findByName(String name);
}

