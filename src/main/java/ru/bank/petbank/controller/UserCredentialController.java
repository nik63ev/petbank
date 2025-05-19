package ru.bank.petbank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.bank.petbank.exception.RegisterException;
import ru.bank.petbank.model.UserCredential;
import ru.bank.petbank.services.UserCredentialService;

@RestController
@RequestMapping("/api")
public class UserCredentialController {
    private final UserCredentialService userCredentialService;

    @Autowired
    public UserCredentialController(UserCredentialService userCredentialService) {
        this.userCredentialService = userCredentialService;
    }

    @GetMapping("/credential/{username}")
    public ResponseEntity<UserCredential> getUser(@PathVariable String username) {
        UserCredential userCredential = userCredentialService.getUserByUsername(username);
        return ResponseEntity.ok(userCredential);
    }

    @PostMapping("/credential/register")
    public ResponseEntity<RegisterResponse> registerUser(@Validated @RequestBody RegisterRequest registerDTO) {
        RegisterResponse registerResponse = userCredentialService.registerUser(registerDTO);
        return ResponseEntity.ok(registerResponse);
    }

    //        RegisterResponse registerResponse = userCredentialService.updateUser(userCredential);
    @PutMapping("/credential/{username}")
    public ResponseEntity<UserCredential> updateUser(@PathVariable String username,
                                                     @RequestBody RegisterRequest updateDTO) {

        UserCredential userCredential = userCredentialService.getUserByUsername(username);
        userCredential.setPassword(updateDTO.getPassword());
        userCredential.setEmail(updateDTO.getEmail());
        UserCredential updateUser = userCredentialService.updateUser(username, userCredential);
        return ResponseEntity.ok(updateUser);
    }

    @DeleteMapping("/credential/{username}")
    public ResponseEntity<UserCredential> deleteUser(@PathVariable String username) {
        UserCredential userCredential = userCredentialService.getUserByUsername(username);
        userCredential = userCredentialService.deleteUser(userCredential.getUsername());
        return ResponseEntity.ok(userCredential);
    }

}