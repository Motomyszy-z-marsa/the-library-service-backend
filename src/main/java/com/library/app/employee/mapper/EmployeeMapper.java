package com.library.app.employee.mapper;

import com.library.app.employee.dto.EmployeeDto;
import com.library.app.employee.model.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeMapper {
    
    public Employee toEmployee(EmployeeDto employeeDto) {
        return new Employee(
                employeeDto.getEmployeeId(),
                employeeDto.getFirstname(),
                employeeDto.getLastname(),
                employeeDto.getPersonalNumber()
        );
    }
    
    public EmployeeDto toEmployeeDto(Employee employee) {
        return new EmployeeDto(
                employee.getId(),
                employee.getFirstname(),
                employee.getLastname(),
                employee.getPersonalNumber()
        );
    }
    
    public List<Employee> toList(List<EmployeeDto> employeeDtos) {
        return employeeDtos.stream()
                .map(this::toEmployee)
                .toList();
    }
    
    public List<EmployeeDto> toDtoList(List<Employee> employees) {
        return employees.stream()
                .map(this::toEmployeeDto)
                .toList();
    }
}
