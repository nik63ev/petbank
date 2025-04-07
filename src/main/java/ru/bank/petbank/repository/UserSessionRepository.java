package ru.bank.petbank.repository;

import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.bank.petbank.model.UserSession;

public interface UserSessionRepository extends JpaRepository<UserSession, Long> {
    UserSession findBySessionToken(String sessionToken);
}
