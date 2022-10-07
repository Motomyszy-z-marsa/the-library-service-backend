package com.library.app.listener;

import com.library.app.account.model.Account;
import com.library.app.register.event.RegistrationCompleteEvent;
import com.library.app.register.service.RegisterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
@Component
public class RegistrationCompleteEventListener implements ApplicationListener<RegistrationCompleteEvent> {
    
    private final RegisterService registerService;
    
    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event) {
        
        Account account = event.getAccount();
        String token = UUID.randomUUID().toString();
        registerService.saveVerificationTokenForAccount(token, account);
        
        String url = event.getUrl() + "verifyRegistration?token=" + token;
        log.info("Click the link to verify account: {} ", url);
    }
}
