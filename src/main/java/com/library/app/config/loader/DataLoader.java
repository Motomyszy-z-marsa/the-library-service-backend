package com.library.app.config.loader;

import com.library.app.account.model.Account;
import com.library.app.account.repository.AccountRepository;
import com.library.app.account.role.Role;
import com.library.app.employee.model.Employee;
import com.library.app.employee.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class DataLoader implements ApplicationRunner {
    
    private final AccountRepository accountRepository;
    private final EmployeeRepository employeeRepository;
    
    public void run(ApplicationArguments args) {
        accountRepository.save(new Account(1L, "John", "Travolta", "test@test.com", "password", Role.ADMIN, false));
        accountRepository.save(new Account(2L, "Jacob", "theuberuser", "test@test.eu", "password123", Role.EMPLOYEE, false));
        accountRepository.save(new Account(3L, "Steven", "brown", "monkey@monkey.pl", "morerandom", Role.EMPLOYEE, false));
        accountRepository.save(new Account(4L, "Mary", "McKinley", "sheep@sheep.eu", "nullshiet", Role.MEMBER, false));
        
        employeeRepository.save(new Employee(1L, "Jacek", "Mrzonka", 1024L));
        employeeRepository.save(new Employee(2L, "Korwin", "Parufka", 3L));
        employeeRepository.save(new Employee(3L, "Neo", "Leo", 112L));
        employeeRepository.save(new Employee(4L, "Zeus", "Morela", 997L));
        
    }
}
