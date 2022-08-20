package com.library.app.employee.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {
    
    private Long employeeId;
    private String firstname;
    private String lastname;
    private Long personalNumber;
}
