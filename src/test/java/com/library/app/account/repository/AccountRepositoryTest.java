package com.library.app.account.repository;

import com.library.app.account.model.Account;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class AccountRepositoryTest extends ContainerBase {
    
    @Autowired
    private AccountRepository accountRepository;
    
    @Test
    public void shouldSaveAccount() {
        //given
        Account underTest = Account.builder()
                .id(1L)
                .username("John")
                .password("password")
                .build();
        
        //when
        final Account saved = accountRepository.save(underTest);
        
        //then
        assertAll(
                () -> assertNotNull(saved),
                () -> assertNotNull(saved.getId()));
    }
    
    @Test
    public void shouldReturnAccountByUsername() {
        //given
        Account underTest = Account.builder()
                .id(1L)
                .username("John")
                .password("password")
                .build();
        accountRepository.save(underTest);
        
        //when
        final Optional<Account> saved = accountRepository.findByUsername(underTest.getUsername());
        
        //then
        
        assertAll(
                () -> assertTrue(saved.isPresent()),
                () -> assertEquals("John", saved.get().getUsername())
        );
    }
    
    
}