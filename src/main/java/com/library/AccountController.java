package com.library.app;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/account")
public class AccountController {
    
    private AccountRepository accountRepository;
    
    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        accountRepository.save(account);
        return new ResponseEntity<>(account, HttpStatus.CREATED);
    }
}
