package com.library.app.account.repository;

import com.library.app.account.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    
    Optional<Account> findByUsername(String username);
}
