package com.library.app.testcontainers.employee.repository;

import com.library.app.AbstractTest;
import com.library.app.employee.model.Employee;
import com.library.app.employee.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@RequiredArgsConstructor
class EmployeeRepositoryTest extends AbstractTest {
    
    private EmployeeRepository employeeRepository;
    
    @Test
    void shouldFetchAddedEmployeesFromDb() {
        //given
        Employee employee1 = Employee.builder()
                .id(1L)
                .firstname("John")
                .lastname("Rambo")
                .personalNumber(1234L)
                .build();
        
        Employee employee2 = Employee.builder()
                .id(2L)
                .firstname("Mary")
                .lastname("Blige")
                .personalNumber(55555L)
                .build();
        
        
        employeeRepository.save(employee1);
        employeeRepository.save(employee2);
        
        //when
        List<Employee> employees = employeeRepository.findAll();
        
        //then
        assertAll(
                () -> assertEquals("Mary", employees.get(1).getFirstname()),
                () -> assertThat(employees).hasSize(2)
        );
        
        //cleanup
        employeeRepository.deleteAll();
    }
}