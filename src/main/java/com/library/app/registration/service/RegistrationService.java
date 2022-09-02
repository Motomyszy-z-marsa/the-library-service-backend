package com.library.app.registration.service;

import com.library.app.account.dto.AccountDto;
import com.library.app.account.repository.AccountRepository;
import com.library.app.account.service.AccountService;
import com.library.app.registration.request.RegistrationRequest;
import com.library.app.registration.validation.email.EmailValidator;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {
    
    private final EmailValidator emailValidator;
    private final AccountService accountService;
    private final AccountRepository accountRepository;
    
    public ResponseEntity<AccountDto> register(RegistrationRequest request) {
        boolean isValidEmail = emailValidator.test(request.getEmail());
        boolean isPresentEmail = accountRepository.findByEmail(request.getEmail()).isPresent();
        
        
        if (!isValidEmail) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        } else if (isPresentEmail) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        } else {
            return new ResponseEntity<>(accountService.signUpAccount(new AccountDto(
                    request.getId(),
                    request.getUsername(),
                    request.getEmail(),
                    request.getPassword(),
                    request.getAccountRole(),
                    request.isLocked(),
                    request.isEnabled()
            )), HttpStatus.CREATED);
        }
    }
}
