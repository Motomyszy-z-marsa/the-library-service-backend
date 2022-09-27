package com.library.app.register.controller;

import com.library.app.account.model.Account;
import com.library.app.register.event.RegistrationCompleteEvent;
import com.library.app.register.service.RegisterService;
import com.library.app.register.token.VerificationToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController("/api")
@RequiredArgsConstructor
@Slf4j
public class RegisterController {
    
    private final RegisterService registerService;
    
    private final ApplicationEventPublisher publisher;
    
    
    @PostMapping("/register")
    public String registerUser(@RequestBody Account model, final HttpServletRequest request) {
        Account account = registerService.registerAccount(model);
        publisher.publishEvent(new RegistrationCompleteEvent(account, applicationUrl(request)));
        return "Success";
    }
    
    @GetMapping("/verifyRegistration")
    public String verifyRegistration(@RequestParam("token") String token) {
        String result = registerService.validateVerificationToken(token);
        if (result.equalsIgnoreCase("valid")) {
            return "Account verification is successful.";
        }
        return "Bad token";
    }
    
    @GetMapping("/resendVerificationToken")
    public String resendVerificationToken(@RequestParam("token") String oldToken, HttpServletRequest request) {
        VerificationToken verificationToken =
                registerService.generateNewVerificationToken(oldToken);
        Account account = verificationToken.getAccount();
        resendVerificationTokenMail(account, applicationUrl(request), verificationToken);
        return "Verification link sent.";
    }
    
    private void resendVerificationTokenMail(final Account account, final String applicationUrl, final VerificationToken verificationToken) {
        String url = applicationUrl +
                "verifyRegistration?token="
                + verificationToken.getToken();
    
        // TODO: 27.09.2022 The token have same expiration date - to be fixed
        // TODO: 25.09.2022 Send an actual email.
        log.info("Click the link to verify your account: {}", url);
    }
    
    private String applicationUrl(final HttpServletRequest request) {
        return "http://" +
                request.getServerName() + ":" +
                request.getServerPort() + "/" +
                request.getContextPath();
    }
    
    @GetMapping("/hello")
    public String hello() {
        return "Hello World";
    }
}
