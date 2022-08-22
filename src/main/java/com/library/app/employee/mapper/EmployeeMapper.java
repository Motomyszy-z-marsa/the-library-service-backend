package com.library.app.employee.mapper;

import com.library.app.employee.dto.EmployeeDto;
import com.library.app.employee.model.Employee;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeMapper {
    
    private final ModelMapper mapper = new ModelMapper();
    
    public EmployeeDto toEmployeeDto(Employee employee) {
        return mapper.map(employee, EmployeeDto.class);
    }
    
    public Employee toEmployee(EmployeeDto employeeDto) {
        return mapper.map(employeeDto, Employee.class);
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
