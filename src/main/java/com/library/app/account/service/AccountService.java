package com.library.app.account.service;

import com.library.app.account.dto.AccountDto;
import com.library.app.account.mapper.AccountMapper;
import com.library.app.account.model.Account;
import com.library.app.account.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private AccountMapper accountMapper;
    
    @Transactional
    public ResponseEntity<List<AccountDto>> getAccountsAsList() {
        if (!accountMapper.toDtoList(accountRepository.findAll()).isEmpty()) {
            return new ResponseEntity<>(accountMapper.toDtoList(accountRepository.findAll()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    public ResponseEntity<AccountDto> getAccountById(final Long id) {
        if (accountRepository.existsById(id)) {
            return new ResponseEntity<>(accountMapper.toAccountDto(accountRepository.getReferenceById(id)), HttpStatus.FOUND);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    public ResponseEntity<AccountDto> createNewAccount(final AccountDto accountDto) {
        final Optional<Account> userFromDb = accountRepository.findByUsername(accountDto.getUsername());
        
        if (userFromDb.isPresent()) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        } else {
            final Account account = accountMapper.toAccount(accountDto);
            final Account saved = accountRepository.save(account);
            
            return new ResponseEntity<>(accountMapper.toAccountDto(saved), HttpStatus.OK);
        }
    }
    
    public ResponseEntity<Void> removeAccount(Long id) {
        if (accountRepository.existsById(id)) {
            accountRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
