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

import static java.util.Collections.singleton;

@Component
@RequiredArgsConstructor
class DataLoader implements ApplicationRunner {
    
    private final AccountRepository accountRepository;
    private final EmployeeRepository employeeRepository;
    
    public void run(ApplicationArguments args) {
        accountRepository.save(new Account(1L, "John", "Travolta", "johnyy", "test@test.com", "password", singleton(new Role(4L, "ADMIN")), false, true));
        accountRepository.save(new Account(2L, "Philadelphia", "Cocky", "theuberuser", "test@test.eu", "randomshiet", singleton(new Role(3L, "EMPLOYEE")), false, true));
        accountRepository.save(new Account(3L, "Becky", "brown", "brownie", "monkey@monkey.pl", "morerandom", singleton(new Role(2L, "MEMBER")), false, true));
        accountRepository.save(new Account(4L, "Suzan", "McKinley", "suzyyy300", "sheep@sheep.eu", "nullshiet", singleton(new Role(1L, "ADMIN")), false, true));
        
        employeeRepository.save(new Employee(1L, "Jacek", "Mrzonka", 1024L));
        employeeRepository.save(new Employee(2L, "Korwin", "Parufka", 3L));
        employeeRepository.save(new Employee(3L, "Neo", "Leo", 112L));
        employeeRepository.save(new Employee(4L, "Zeus", "Morela", 997L));
        
    }
}
