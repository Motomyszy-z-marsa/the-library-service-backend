package com.library.app.testcontainers.account.repository;

import com.library.app.account.model.Account;
import com.library.app.account.repository.AccountRepository;
import com.library.app.AbstractTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class AccountRepositoryTest extends AbstractTest {
    
    @Autowired
    private AccountRepository accountRepository;
    
    @Test
    void shouldFetchAddedAccountsFromDb() {
        //given
        Account account1 = Account.builder()
                .id(1L)
                .username("username")
                .password("password")
                .build();
        
        Account account2 = Account.builder()
                .id(2L)
                .username("user2")
                .password("pass2")
                .build();
        
        accountRepository.save(account1);
        accountRepository.save(account2);
        
        //when
        List<Account> accounts = accountRepository.findAll();
        
        //then
        assertThat(accounts).hasSize(2);
        Assertions.assertEquals("username", accounts.get(0).getUsername());
        
        //cleanup
        accountRepository.deleteAll();
    }
}