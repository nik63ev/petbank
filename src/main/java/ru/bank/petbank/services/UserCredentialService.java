package ru.bank.petbank.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bank.petbank.model.UserCredential;
import ru.bank.petbank.repository.UserCredentialRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserCredentialService {
    private final UserCredentialRepository userCredentialRepository;

    @Autowired
    public UserCredentialService(UserCredentialRepository userCredentialRepository) {
        this.userCredentialRepository = userCredentialRepository;
    }

    @Transactional
    public UserCredential registerUser(UserCredential userCredential) {
        if (userCredentialRepository.existsByUsername(userCredential.getUsername())) {
            throw new RuntimeException("Username is already taken");
        }

        if (userCredentialRepository.existsByEmail(userCredential.getEmail())) {
            throw new RuntimeException("Email is already in use");
        }

        // В реальном приложении здесь нужно хэшировать пароль!
        return userCredentialRepository.save(userCredential);
    }

    public UserCredential getUserByUsername(String username) {
        return userCredentialRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
