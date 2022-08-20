package com.library.app.account.controller;

import com.library.app.account.dto.AccountDto;
import com.library.app.account.repository.AccountRepository;
import com.library.app.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/account")
final class AccountController {
    
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private AccountService accountService;
    
    @PostMapping
    public ResponseEntity<AccountDto> createAccount(@RequestBody AccountDto accountDto) {
        return new ResponseEntity<>(accountService.execute(accountDto), HttpStatus.CREATED);
    }
    
    @GetMapping
    public ResponseEntity<List<AccountDto>> getAccounts() {
        return new ResponseEntity<>(accountService.getAllAccounts(), HttpStatus.ACCEPTED);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable Long id) {
        return new ResponseEntity<>(accountService.getAccountById(id), HttpStatus.FOUND);
    }
}
