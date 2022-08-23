package com.library.app.employee.repository;

import com.library.app.employee.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByPersonalNumber(Long personalNumber);
}
