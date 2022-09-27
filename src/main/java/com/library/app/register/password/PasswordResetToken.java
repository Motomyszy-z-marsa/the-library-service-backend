package com.library.app.register.password;

import com.library.app.account.model.Account;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
public class PasswordResetToken {
    
    private static final int EXPIRATION_TIME = 10;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String token;
    
    private Date expirationTime;
    
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "account_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "FK_ACCOUNT_PASSWORD_TOKEN"))
    private Account account;
    
    public PasswordResetToken(Account account, String token) {
        this.token = token;
        this.account = account;
        this.expirationTime = calculateExpirationDate(EXPIRATION_TIME);
    }
    
    public PasswordResetToken(String token) {
        super();
        this.token = token;
        this.expirationTime = calculateExpirationDate(EXPIRATION_TIME);
    }
    
    private Date calculateExpirationDate(int expirationTime) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(new Date().getTime());
        calendar.add(Calendar.MINUTE, expirationTime);
        return new Date(calendar.getTime().getTime());
    }
}
