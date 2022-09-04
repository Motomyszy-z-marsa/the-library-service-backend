package com.library.app.account.mapper;

import com.library.app.account.dto.AccountDto;
import com.library.app.account.model.Account;
import com.library.app.account.role.Role;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.singleton;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AccountMapperTest {
    
    private final AccountMapper accountMapper = new AccountMapper();
    
    @Test
    void shouldMapAccountToAccountDto() {
        //given
        final Account underTest = new Account(1L, "Jack",
                "Sparrow", "Login", "email",
                "password", singleton(new Role(1L, "MEMBER")),
                false, true);
        
        //when
        AccountDto accountDto = accountMapper.toAccountDto(underTest);
        
        //then
        assertAll(
                () -> assertEquals(1L, accountDto.getId()),
                () -> assertEquals("Login", accountDto.getUsername()),
                () -> assertEquals("password", accountDto.getPassword())
        );
    }
    
    @Test
    void shouldMapAccountDtoToAccount() {
        //given
        final AccountDto underTest = new AccountDto(2L, "John",
                "Neo", "marcello007", "test@test.com",
                "marcell", singleton(new Role(1L, "ADMIN")),
                false, true);
        
        //when
        final Account account = accountMapper.toAccount(underTest);
        
        //then
        assertAll(
                () -> assertEquals(2L, account.getId()),
                () -> assertEquals("marcello007", account.getUsername()),
                () -> assertEquals("marcell", account.getPassword())
        );
    }
    
    @Test
    void shouldMapAccountListToAccountDtoList() {
        //given
        final List<Account> underTestList = new ArrayList<>();
        final Account login1 = new Account(1L, "firstname",
                "lastname", "login1", "email",
                "password", singleton(new Role(1L, "ADMIN")),
                false, true);
        final Account login2 = new Account(2L, "firstname2",
                "lastname2", "login2", "email",
                "password2", singleton(new Role(2L, "ADMIN")),
                false, true);
        underTestList.add(login1);
        underTestList.add(login2);
        
        //when
        final List<AccountDto> dtos = accountMapper.toDtoList(underTestList);
        
        //then
        assertAll(
                () -> assertEquals(2, dtos.size()),
                () -> assertEquals("login1", dtos.get(0).getUsername()),
                () -> assertEquals("password2", dtos.get(1).getPassword())
        );
        
    }
    
    @Test
    void shouldMapAccountDtoListToAccountList() {
        //given
        final List<AccountDto> underTestDtoList = new ArrayList<>();
        final AccountDto login1 = new AccountDto(1L, "firstname1",
                "lastname1", "login1", "test@yo.com",
                "password1", singleton(new Role(1L, "ADMIN")),
                false, true);
        final AccountDto login2 = new AccountDto(2L, "firstname2",
                "lastname2", "login2", "test@yo.com",
                "password2", singleton(new Role(2L, "MEMBER")),
                false, true);
        underTestDtoList.add(login1);
        underTestDtoList.add(login2);
        
        //when
        final List<Account> accounts = accountMapper.toList(underTestDtoList);
        
        //then
        assertAll(
                () -> assertEquals(2, accounts.size()),
                () -> assertEquals(1L, accounts.get(0).getId()),
                () -> assertEquals("login2", accounts.get(1).getUsername())
        );
    }
}