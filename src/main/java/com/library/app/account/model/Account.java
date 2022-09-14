package com.library.app.account.model;

import com.library.app.account.role.Role;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class Account {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String firstname;
    @Column(nullable = false)
    private String lastname;
    @Column(nullable = false, length = 48)
    private String email;
    @Column(nullable = false, length = 64)
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    private boolean isEnabled = false;
}
