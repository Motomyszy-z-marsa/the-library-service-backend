package com.library.app.account.controller;

import com.library.app.account.dto.AccountDto;
import com.library.app.account.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/account")
@CrossOrigin("http://localhost:3000")
final class AccountController {
    
    private final AccountService accountService;
    
    private AccountController(final AccountService accountService) {
        this.accountService = accountService;
    }
    
    @PostMapping
    public ResponseEntity<AccountDto> createAccount(@RequestBody AccountDto accountDto) {
        return accountService.create(accountDto);
    }
    
    @GetMapping
    public ResponseEntity<List<AccountDto>> getAccountsAsList() {
        return accountService.getAsList();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable Long id) {
        return accountService.getById(id);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeAccount(@PathVariable Long id) {
        return accountService.remove(id);
    }
}
