package com.library.app.account.dto;

import com.library.app.account.role.Role;
import lombok.*;

import java.util.Collection;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {
    
    private Long id;
    private String firstname;
    private String lastname;
    private String username;
    private String email;
    private String password;
    private Collection<Role> roles;
    private boolean locked;
    private boolean enabled;
}

