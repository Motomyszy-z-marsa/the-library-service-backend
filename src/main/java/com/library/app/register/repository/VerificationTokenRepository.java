package com.library.app.register.repository;

import com.library.app.register.token.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {
    
    VerificationToken findByToken(String token);
}
