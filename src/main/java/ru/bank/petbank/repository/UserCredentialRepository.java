package ru.bank.petbank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ru.bank.petbank.model.UserCredential;

import java.util.Optional;

@Repository
//@Component("userCredentialRepository")
public interface UserCredentialRepository extends JpaRepository<UserCredential, Long> {

    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    Optional<UserCredential> findByUsername(String username);
}