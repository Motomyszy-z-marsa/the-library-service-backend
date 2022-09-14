package com.library.app.register.controller;

import com.library.app.account.model.Account;
import com.library.app.register.event.RegistrationCompleteEvent;
import com.library.app.register.service.RegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController("/api")
@RequiredArgsConstructor
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
