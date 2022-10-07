package com.library.app.account.service;

import com.library.app.account.dto.AccountDto;
import com.library.app.account.mapper.AccountMapper;
import com.library.app.account.model.Account;
import com.library.app.account.repository.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AccountService {
    
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    
    public ResponseEntity<List<AccountDto>> getAsList() {
        if (!accountMapper.toDtoList(accountRepository.findAll()).isEmpty()) {
            return new ResponseEntity<>(accountMapper.toDtoList(accountRepository.findAll()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    public ResponseEntity<AccountDto> getById(final Long id) {
        if (accountRepository.existsById(id)) {
            return new ResponseEntity<>(accountMapper.toAccountDto(accountRepository.getReferenceById(id)), HttpStatus.FOUND);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    public ResponseEntity<AccountDto> create(final AccountDto accountDto) {
        final Account userFromDb = accountRepository.findByEmail(accountDto.getEmail());
        
        if (userFromDb != null) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        } else {
            final Account saved = accountRepository.save(accountMapper.toAccount(accountDto));
            return new ResponseEntity<>(accountMapper.toAccountDto(saved), HttpStatus.OK);
        }
    }
    
    public ResponseEntity<Void> remove(Long id) {
        if (accountRepository.existsById(id)) {
            accountRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    public Account findByEmail(String email) {
        return accountRepository.findByEmail(email);
    }
}
