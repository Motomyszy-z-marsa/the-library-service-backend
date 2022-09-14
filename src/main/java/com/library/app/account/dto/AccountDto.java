package com.library.app.account.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {
    
    private Long id;
    @NotEmpty
    private String firstname;
    @NotEmpty
    private String lastname;
    @Email
    @NotEmpty(message = "Email cannot be empty.")
    private String email;
    @NotEmpty(message = "Password cannot be empty.")
    private String password;
    private String matchingPassword;
}

