package com.library.app.account.mapper;

import com.library.app.account.dto.AccountDto;
import com.library.app.account.model.Account;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountMapper {
    
    public AccountDto toAccountDto(Account account) {
        return new AccountDto(
                account.getId(),
                account.getLogin(),
                account.getPassword()
        );
    }
    
    public Account toAccount(AccountDto accountDto) {
        return new Account(
                accountDto.getUserId(),
                accountDto.getLogin(),
                accountDto.getPassword()
        );
    }
    
    public List<AccountDto> toDtoList(List<Account> accounts) {
        return accounts.stream()
                .map(this::toAccountDto)
                .toList();
    }
    
    public List<Account> toList(List<AccountDto> accountDtos) {
        return accountDtos.stream()
                .map(this::toAccount)
                .toList();
    }
}
