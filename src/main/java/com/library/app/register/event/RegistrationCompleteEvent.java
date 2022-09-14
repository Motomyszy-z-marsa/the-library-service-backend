package com.library.app.register.event;

import com.library.app.account.model.Account;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class RegistrationCompleteEvent extends ApplicationEvent {
    
    private Account account;
    private String url;
    
    public RegistrationCompleteEvent(final Account account, final String url) {
        super(account);
        this.account = account;
        this.url = url;
    }
}
