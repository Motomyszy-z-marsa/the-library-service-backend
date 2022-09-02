package com.library.app.config.loader;

import com.library.app.account.model.Account;
import com.library.app.account.repository.AccountRepository;
import com.library.app.employee.model.Employee;
import com.library.app.employee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {
    
    private AccountRepository accountRepository;
    private EmployeeRepository employeeRepository;
    
    @Autowired
    public DataLoader(AccountRepository accountRepository, EmployeeRepository employeeRepository) {
        this.accountRepository = accountRepository;
        this.employeeRepository = employeeRepository;
    }
    
    public void run(ApplicationArguments args) {
        accountRepository.save(new Account(1L, "John", "password"));
        accountRepository.save(new Account(2L, "Philadelphia", "randomshiet"));
        accountRepository.save(new Account(3L, "Becky", "morerandom"));
        accountRepository.save(new Account(4L, "Suzan", "nullshiet"));
        
        employeeRepository.save(new Employee(1L, "Jacek", "Mrzonka", 1024L));
        employeeRepository.save(new Employee(2L, "Korwin", "Parufka", 3L));
        employeeRepository.save(new Employee(3L, "Neo", "Leo", 112L));
        employeeRepository.save(new Employee(4L, "Zeus", "Morela", 997L));
        
    }
}
