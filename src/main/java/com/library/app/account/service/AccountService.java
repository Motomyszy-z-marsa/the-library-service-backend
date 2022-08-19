package com.library.app.account.service;

import com.library.app.account.Account;
import com.library.app.account.dto.AccountDto;
import com.library.app.account.mapper.AccountMapper;
import com.library.app.account.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {
    
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private AccountMapper accountMapper;
    
    public List<AccountDto> getAllAccounts() {
        return accountMapper.toDtoList(accountRepository.findAll());
    }
    
    public AccountDto getAccountById(Long id) {
        return accountMapper.toAccountDto(accountRepository.getReferenceById(id));
    }
    
    public AccountDto execute(final AccountDto accountDto) {
        final Account account = accountMapper.toAccount(accountDto);
        final Account saved = accountRepository.save(account);
        
        return accountMapper.toAccountDto(saved);
    }
}
