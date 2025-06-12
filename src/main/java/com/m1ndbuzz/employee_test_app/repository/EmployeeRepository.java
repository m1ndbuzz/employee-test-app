package com.m1ndbuzz.employee_test_app.repository;


import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.m1ndbuzz.employee_test_app.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findByName(String name);

    @Query("""
        SELECT e FROM Employee e 
        JOIN e.team t 
        LEFT JOIN t.teamLead tl 
        WHERE (:name IS NULL OR LOWER(e.name) LIKE LOWER(CONCAT('%', :name, '%')))
          AND (:teamName IS NULL OR LOWER(t.name) LIKE LOWER(CONCAT('%', :teamName, '%')))
          AND (:teamLeadName IS NULL OR LOWER(tl.name) LIKE LOWER(CONCAT('%', :teamLeadName, '%')))
        """)
    Page<Employee> search(
            @Param("name") String name,
            @Param("teamName") String teamName,
            @Param("teamLeadName") String teamLeadName,
            Pageable pageable);
}

