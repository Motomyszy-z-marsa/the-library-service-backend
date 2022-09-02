package com.library.app.registration.controller;

import com.library.app.account.dto.AccountDto;
import com.library.app.registration.request.RegistrationRequest;
import com.library.app.registration.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/registration")
@RequiredArgsConstructor
public class AccountRegistrationController {
    
    private final RegistrationService registrationService;
    
    @PostMapping
    public ResponseEntity<AccountDto> register(@RequestBody RegistrationRequest request) {
        return registrationService.register(request);
    }
}
