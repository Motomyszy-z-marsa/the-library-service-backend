package com.library.app.employee.controller;

import com.library.app.employee.dto.EmployeeDto;
import com.library.app.employee.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
//@CrossOrigin("http://localhost:3000")
@RequiredArgsConstructor
public class EmployeeController {
    
    private final EmployeeService employeeService;
    
    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getEmployeesAsList() {
        return employeeService.getAsList();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable Long id) {
        return employeeService.getById(id);
    }
    
    @PostMapping
    public ResponseEntity<EmployeeDto> createNewEmployee(@RequestBody EmployeeDto employeeDto) {
        return employeeService.create(employeeDto);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeAccount(@PathVariable Long id) {
        return employeeService.remove(id);
    }
}
