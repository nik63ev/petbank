package ru.bank.petbank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.bank.petbank.model.UserCredential;
import ru.bank.petbank.services.UserCredentialService;

@RestController
@RequestMapping("/api/credential")
public class UserCredentialController {
    private final UserCredentialService userCredentialService;

    @Autowired
    public UserCredentialController(UserCredentialService userCredentialService) {
        this.userCredentialService = userCredentialService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserCredential> registerUser(@Validated @RequestBody UserCredential userCredential) {
        UserCredential registeredUserCredential = userCredentialService.registerUser(userCredential);
        return ResponseEntity.ok(registeredUserCredential);
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserCredential> getUser(@PathVariable String username) {
        UserCredential userCredential = userCredentialService.getUserByUsername(username);
        return ResponseEntity.ok(userCredential);
    }
}