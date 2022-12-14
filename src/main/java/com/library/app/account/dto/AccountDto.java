package com.library.app.account.dto;

import com.library.app.account.role.AccountRole;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {
    
    private Long id;
    private String username;
    private String email;
    private String password;
    private AccountRole accountRole;
    private boolean locked;
    private boolean enabled;
}

