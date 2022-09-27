package com.library.app.register.service;

import com.library.app.account.model.Account;
import com.library.app.account.repository.AccountRepository;
import com.library.app.account.role.Role;
import com.library.app.register.password.PasswordResetToken;
import com.library.app.register.password.PasswordResetTokenRepository;
import com.library.app.register.repository.VerificationTokenRepository;
import com.library.app.register.token.VerificationToken;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RegisterService {
    
    private final AccountRepository accountRepository;
    private final VerificationTokenRepository verificationTokenRepository;
    private final PasswordResetTokenRepository passwordResetTokenRepository;
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
    
    public VerificationToken generateNewVerificationToken(final String oldToken) {
        VerificationToken verificationToken = verificationTokenRepository.findByToken(oldToken);
        verificationToken.setToken(UUID.randomUUID().toString());
        verificationTokenRepository.save(verificationToken);
        
        return verificationToken;
    }
    
    public Account findAccountByEmail(final String email) {
        return accountRepository.findByEmail(email);
    }
    
    public void createPasswordResetTokenForAccount(final Account account, final String token) {
        PasswordResetToken passwordResetToken = new PasswordResetToken(account, token);
        passwordResetTokenRepository.save(passwordResetToken);
    }
    
    public String validatePasswordResetToken(final String token) {
        PasswordResetToken passwordResetToken = passwordResetTokenRepository.findByToken(token);
        
        if (passwordResetToken == null) {
            return "invalid password";
        }
        Account account = passwordResetToken.getAccount();
        Calendar calendar = Calendar.getInstance();
        
        if (passwordResetToken.getExpirationTime().getTime() - calendar.getTime().getTime() <= 0) {
            passwordResetTokenRepository.delete(passwordResetToken);
            return "expired";
        }
        return "valid";
    }
    
    public Optional<Account> getAccountByPasswordResetToken(final String token) {
        return Optional.ofNullable(passwordResetTokenRepository.findByToken(token).getAccount());
    }
    
    public void changePassword(final Account account, final String newPassword) {
        account.setPassword(passwordEncoder.encode(newPassword));
        accountRepository.save(account);
    }
}
