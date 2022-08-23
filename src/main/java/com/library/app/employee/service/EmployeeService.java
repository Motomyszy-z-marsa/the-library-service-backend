package com.library.app.employee.service;

import com.library.app.employee.dto.EmployeeDto;
import com.library.app.employee.mapper.EmployeeMapper;
import com.library.app.employee.model.Employee;
import com.library.app.employee.repository.EmployeeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    
    
    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;
    
    public EmployeeService(final EmployeeRepository employeeRepository, final EmployeeMapper employeeMapper) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
    }
    
    public ResponseEntity<List<EmployeeDto>> getAsList() {
        if (!employeeMapper.toDtoList(employeeRepository.findAll()).isEmpty()) {
            return new ResponseEntity<>(employeeMapper.toDtoList(employeeRepository.findAll()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    public ResponseEntity<EmployeeDto> getById(final Long id) {
        if (employeeRepository.existsById(id)) {
            return new ResponseEntity<>(employeeMapper.toEmployeeDto(employeeRepository.getReferenceById(id)), HttpStatus.FOUND);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    public ResponseEntity<EmployeeDto> create(final EmployeeDto employeeDto) {
        final Optional<Employee> employeeFromDb = employeeRepository.findByPersonalNumber(employeeDto.getPersonalNumber());
        
        if (employeeFromDb.isPresent()) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        } else {
            final Employee saved = employeeRepository.save(employeeMapper.toEmployee(employeeDto));
            
            return new ResponseEntity<>(employeeMapper.toEmployeeDto(saved), HttpStatus.OK);
        }
    }
    
    public ResponseEntity<Void> remove(Long id) {
        if (employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
