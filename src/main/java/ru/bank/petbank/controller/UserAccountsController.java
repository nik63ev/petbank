package ru.bank.petbank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.bank.petbank.model.UserAccounts;
import ru.bank.petbank.services.UserAccountsService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("api/accounts")
public class UserAccountsController {
    private final UserAccountsService userAccountsService;

    @Autowired
    public UserAccountsController(UserAccountsService userAccountsService) {
        this.userAccountsService = userAccountsService;
    }

    @GetMapping("/{userInfoId}")
    public ResponseEntity<List<UserAccounts>> getUserAccounts(@PathVariable("userInfoId") Long userInfoId) {
        Optional<List<UserAccounts>> userAccounts = userAccountsService.getUserAccounts(userInfoId);
        return ResponseEntity.ok(userAccounts.get());
    }

    @PostMapping("/create_account")
    public ResponseEntity<CreateAccountResponse> createUserAccounts
            (@RequestBody CreateAccountRequest createAccountRequest) {
        CreateAccountResponse createAccountResponse = userAccountsService.createUserAccount(createAccountRequest);
        return ResponseEntity.ok(createAccountResponse);
    }

    @DeleteMapping("/{account_number}")
    public ResponseEntity<UserAccounts> deleteUserAccounts(@PathVariable("account_number") String accountNumber) {
        UserAccounts userAccount = userAccountsService.deleteUserAccount(accountNumber);
        return ResponseEntity.ok(userAccount);
    }
}
