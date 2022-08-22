package com.library.app.employee.mapper;

import com.library.app.employee.dto.EmployeeDto;
import com.library.app.employee.model.Employee;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class EmployeeMapperTest {
    
    private final EmployeeMapper employeeMapper = new EmployeeMapper();
    
    @Test
    void shouldMapEmployeeToEmployeeDto() {
        //given
        final Employee underTest = new Employee(2L, "Mary", "Johnson", 22222L);
        
        //when
        final EmployeeDto dto = employeeMapper.toEmployeeDto(underTest);
        
        //then
        assertAll("map Employee to EmployeeDto",
                () -> assertEquals(2L, dto.getEmployeeId()),
                () -> assertEquals("Mary", dto.getFirstname()),
                () -> assertEquals("Johnson", dto.getLastname()),
                () -> assertEquals(22222L, dto.getPersonalNumber())
        );
    }
    
    @Test
    void shouldMapEmployeeDtoToEmployee() {
        //given
        final EmployeeDto underTest = new EmployeeDto(1L, "John", "Rambo", 123456L);
        
        //when
        final Employee employee = employeeMapper.toEmployee(underTest);
        
        //then
        assertAll(
                () -> assertEquals(1L, employee.getId()),
                () -> assertEquals("John", employee.getFirstname()),
                () -> assertEquals("Rambo", employee.getLastname()),
                () -> assertEquals(123456L, employee.getPersonalNumber())
        );
        
    }
    
    @Test
    void shouldMapEmployeeDtoListToEmployeeList() {
        //given
        final List<EmployeeDto> underTestList = new ArrayList<>();
        final EmployeeDto janet = new EmployeeDto(1L, "Janet", "Jackson", 1234L);
        final EmployeeDto joe = new EmployeeDto(3L, "Joe", "Ribery", 4321L);
        underTestList.add(janet);
        underTestList.add(joe);
        
        //when
        final List<Employee> employees = employeeMapper.toList(underTestList);
        
        //then
        assertAll(
                () -> assertEquals(2, employees.size()),
                () -> assertEquals("Janet", employees.get(0).getFirstname()),
                () -> assertEquals(3L, employees.get(1).getId()),
                () -> assertEquals(4321L, employees.get(1).getPersonalNumber())
        );
        
    }
    
    @Test
    void shouldMapEmployeeListToEmployeeDtoList() {
        //given
        final List<Employee> underTestList = new ArrayList<>();
        final Employee michael = new Employee(1L, "Michael", "Gray", 123L);
        final Employee suzan = new Employee(22L, "Suzan", "Bay", 7777L);
        underTestList.add(michael);
        underTestList.add(suzan);
        
        //when
        final List<EmployeeDto> dtos = employeeMapper.toDtoList(underTestList);
        
        //then
        assertAll(
                () -> assertEquals(2, dtos.size()),
                () -> assertEquals("Michael", dtos.get(0).getFirstname()),
                () -> assertEquals(22L, dtos.get(1).getEmployeeId()),
                () -> assertEquals(7777L, dtos.get(1).getPersonalNumber())
        );
    }
}