package com.library.app.registration.request;

import com.library.app.account.role.Role;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Collection;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RegistrationRequest {
    
    private Long id;
    private String firstname;
    private String lastname;
    private String username;
    private String email;
    private String password;
    private Collection<Role> role;
    private boolean locked;
    private boolean enabled;
}


