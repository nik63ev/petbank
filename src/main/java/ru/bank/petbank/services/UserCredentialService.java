package ru.bank.petbank.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bank.petbank.controller.RegisterRequest;
import ru.bank.petbank.controller.RegisterResponse;
import ru.bank.petbank.controller.Status;
import ru.bank.petbank.exception.RegisterException;
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
    public RegisterResponse registerUser(RegisterRequest registerRequest) {
        if (userCredentialRepository.existsByUsername(registerRequest.getUsername())) {
            throw new RegisterException("Username is already taken");
        }

        if (userCredentialRepository.existsByEmail(registerRequest.getEmail())) {
            throw new RegisterException("Email is already in use");
        }

        // В реальном приложении здесь нужно хэшировать пароль!
        UserCredential userCredential = new UserCredential(registerRequest.getUsername(),
                                                            registerRequest.getPassword(),
                                                            registerRequest.getEmail());
        userCredentialRepository.save(userCredential);
        RegisterResponse registerResponse = new RegisterResponse();
        registerResponse.setStatus(new Status());
        registerResponse.getStatus().setMessage("Successfully registered");
        registerResponse.getStatus().setCode(0);
        registerResponse.setUserId(userCredential.getUserid());
        return registerResponse;
    }

    public UserCredential getUserByUsername(String username) {

        return userCredentialRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
