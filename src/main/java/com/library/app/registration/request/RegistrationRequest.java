package com.library.app.registration.request;

import com.library.app.account.role.AccountRole;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RegistrationRequest {
    
    private Long id;
    private String username;
    private String email;
    private String password;
    private AccountRole accountRole;
    private boolean locked;
    private boolean enabled;
}


