package com.library.app.register.password;

import lombok.Data;

@Data
public class Password {
    
    private String email;
    private String oldPassword;
    private String newPassword;
}
