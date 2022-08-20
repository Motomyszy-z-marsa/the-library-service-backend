package com.library.app.employee.service;

import com.library.app.employee.dto.EmployeeDto;
import com.library.app.employee.mapper.EmployeeMapper;
import com.library.app.employee.model.Employee;
import com.library.app.employee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private EmployeeMapper employeeMapper;
    
    public List<EmployeeDto> getAllEmployees() {
        return employeeMapper.toDtoList(employeeRepository.findAll());
    }
    
    public EmployeeDto getById(Long id) {
        return employeeMapper.toEmployeeDto(employeeRepository.getReferenceById(id));
    }
    
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee saved = employeeRepository.save(employeeMapper.toEmployee(employeeDto));
        return employeeMapper.toEmployeeDto(saved);
    }
}
