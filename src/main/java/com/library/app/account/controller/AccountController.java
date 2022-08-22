package com.library.app.account.controller;

import com.library.app.account.dto.AccountDto;
import com.library.app.account.service.AccountService;
import com.library.app.exception.NoResourceFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
final class AccountController {
    
    private final AccountService accountService;
    
    AccountController(final AccountService accountService) {
        this.accountService = accountService;
    }
    
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
    
    @DeleteMapping("/{id}") // TODO: 22.08.2022 Handle exception when resource is already gone
    public ResponseEntity<AccountDto> removeAccount(@PathVariable Long id) throws NoResourceFoundException {
        accountService.remove(id);
        return new ResponseEntity<>(HttpStatus.GONE);
    }
}
