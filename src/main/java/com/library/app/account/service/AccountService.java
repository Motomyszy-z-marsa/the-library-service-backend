package com.library.app.account.service;

import com.library.app.account.dto.AccountDto;
import com.library.app.account.mapper.AccountMapper;
import com.library.app.account.model.Account;
import com.library.app.account.repository.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AccountService implements UserDetailsService {
    
    
    private final static String ACCOUNT_NOT_FOUND = "Account with email %s not found";
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    
    
    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        return accountRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(ACCOUNT_NOT_FOUND, username)));
    }
    
    public AccountDto signUpAccount(final AccountDto accountdto) {
        boolean accountExists = accountRepository.findByEmail(accountdto.getEmail()).isPresent();
        
        if (accountExists) {
            throw new IllegalStateException("Email already taken.");
        }
        
        final String encodedPassword = bCryptPasswordEncoder.encode(accountdto.getPassword());
        
        final Account account = accountMapper.toAccount(accountdto);
        account.setPassword(encodedPassword);
    
        accountRepository.save(account);
        
        // TODO: 02.09.2022 send confirmation TOKEN
        return accountMapper.toAccountDto(account);
    }
    
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
        final Optional<Account> userFromDb = accountRepository.findByUsername(accountDto.getUsername());
        
        if (userFromDb.isPresent()) {
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
}
