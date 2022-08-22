package com.library.app.account.mapper;

import com.library.app.account.dto.AccountDto;
import com.library.app.account.model.Account;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountMapper {
    
    private final ModelMapper mapper = new ModelMapper();
    
    public AccountDto toAccountDto(Account account) {
        return mapper.map(account, AccountDto.class);
    }
    
    public Account toAccount(AccountDto accountDto) {
        return mapper.map(accountDto, Account.class);
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
