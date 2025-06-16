package ru.bank.petbank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.bank.petbank.model.UserAccounts;
import ru.bank.petbank.services.UserAccountsService;

@Controller
@RequestMapping("api/accounts")
public class UserAccountsController {
    private final UserAccountsService userAccountsService;

    @Autowired
    public UserAccountsController(UserAccountsService userAccountsService) {
        this.userAccountsService = userAccountsService;
    }

    @GetMapping("/{userInfoId}")
    public ResponseEntity<UserAccounts> getUserAccounts(@PathVariable("userInfoId") Long userInfoId) {
        UserAccounts userAccounts = userAccountsService.getUserAccounts(userInfoId);
        return ResponseEntity.ok(userAccounts);
    }

    @PostMapping("/create")
    public ResponseEntity<UserAccounts> createUserAccounts(@RequestBody UserAccounts userAccounts) {

        return null;
    }
}
