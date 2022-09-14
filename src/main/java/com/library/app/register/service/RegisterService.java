package com.library.app.register.service;

import com.library.app.account.model.Account;
import com.library.app.account.repository.AccountRepository;
import com.library.app.account.role.Role;
import com.library.app.register.repository.VerificationTokenRepository;
import com.library.app.register.token.VerificationToken;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;

@Service
@RequiredArgsConstructor
public class RegisterService {
    
    private final AccountRepository accountRepository;
    private final VerificationTokenRepository verificationTokenRepository;
    private final PasswordEncoder passwordEncoder;
    
    public Account registerAccount(Account account) {
        account.setRole(Role.EMPLOYEE);
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        
        accountRepository.save(account);
        return account;
    }
    
    public void saveVerificationTokenForAccount(String token, Account account) {
        VerificationToken verificationToken = new VerificationToken(account, token);
        
        verificationTokenRepository.save(verificationToken);
    }
    
    public String validateVerificationToken(String token) {
        VerificationToken verificationToken = verificationTokenRepository.findByToken(token);
        
        if (verificationToken == null) {
            return "invalid token";
        }
        Account account = verificationToken.getAccount();
        Calendar calendar = Calendar.getInstance();
        
        if (verificationToken.getExpirationTime().getTime() - calendar.getTime().getTime() <= 0) {
            verificationTokenRepository.delete(verificationToken);
            return "expired";
        }
        account.setEnabled(true);
        accountRepository.save(account);
        return "valid";
    }
}
