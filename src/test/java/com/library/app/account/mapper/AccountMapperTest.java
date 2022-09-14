package com.library.app.account.mapper;

import com.library.app.account.dto.Account;
import com.library.app.account.role.Role;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AccountMapperTest {
    
    private final AccountMapper accountMapper = new AccountMapper();
    
    @Test
    void shouldMapAccountToAccountDto() {
        //given
        final com.library.app.account.model.Account underTest = new com.library.app.account.model.Account(1L, "Jack",
                "Sparrow", "email", Role.MEMBER);
        
        //when
        Account account = accountMapper.toAccountDto(underTest);
        
        //then
        assertAll(
                () -> assertEquals(1L, account.getId()),
                () -> assertEquals("Jack", account.getFirstname()),
                () -> assertEquals("password", account.getPassword())
        );
    }
    
    @Test
    void shouldMapAccountDtoToAccount() {
        //given
        final Account underTest = new Account(2L, "John",
                "Neo", "test@test.com",
                "marcell");
        
        //when
        final com.library.app.account.model.Account account = accountMapper.toAccount(underTest);
        
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
        final List<com.library.app.account.model.Account> underTestList = new ArrayList<>();
        final com.library.app.account.model.Account login1 = new com.library.app.account.model.Account(1L, "firstname",
                "lastname", "email", Role.ADMIN);
        final com.library.app.account.model.Account login2 = new com.library.app.account.model.Account(2L, "firstname2",
                "email", "password2", Role.EMPLOYEE);
        underTestList.add(login1);
        underTestList.add(login2);
        
        //when
        final List<Account> dtos = accountMapper.toDtoList(underTestList);
        
        //then
        assertAll(
                () -> assertEquals(2, dtos.size()),
                () -> assertEquals("firstname", dtos.get(0).getFirstname()),
                () -> assertEquals("password2", dtos.get(1).getPassword())
        );
        
    }
    
    @Test
    void shouldMapAccountDtoListToAccountList() {
        //given
        final List<Account> underTestDtoList = new ArrayList<>();
        final Account login1 = new Account(1L, "firstname1",
                "lastname1", "test@yo.com",
                "password1");
        final Account login2 = new Account(2L, "firstname2",
                "lastname2", "test@yo.com",
                "password2");
        underTestDtoList.add(login1);
        underTestDtoList.add(login2);
        
        //when
        final List<com.library.app.account.model.Account> accounts = accountMapper.toList(underTestDtoList);
        
        //then
        assertAll(
                () -> assertEquals(2, accounts.size()),
                () -> assertEquals(1L, accounts.get(0).getId()),
                () -> assertEquals("login2", accounts.get(1).getUsername())
        );
    }
}