package com.library.app.account.repository;

import com.library.app.account.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByEmail(String username);
}
