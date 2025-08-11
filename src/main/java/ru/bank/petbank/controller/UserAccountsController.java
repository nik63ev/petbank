package ru.bank.petbank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.bank.petbank.DTO.CreateAccountRequest;
import ru.bank.petbank.DTO.CreateAccountResponse;
import ru.bank.petbank.DTO.DeleteAccountResponse;
import ru.bank.petbank.model.UserAccounts;
import ru.bank.petbank.services.UserAccountsService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("api/accounts")
public class UserAccountsController {

    @Autowired
    private UserAccountsService userAccountsService;

    @GetMapping("/{userInfoId}")
    public ResponseEntity<List<UserAccounts>> getUserAccounts(@PathVariable Long userInfoId) {
        Optional<List<UserAccounts>> userAccounts = userAccountsService.getUserAccounts(userInfoId);
        return ResponseEntity.ok(userAccounts.get());
    }

    @PostMapping("/create_account")
    public ResponseEntity<CreateAccountResponse> createUserAccounts
            (@RequestBody CreateAccountRequest createAccountRequest) {
        CreateAccountResponse createAccountResponse = userAccountsService.createUserAccount(createAccountRequest);
        return ResponseEntity.ok(createAccountResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteAccountResponse> deleteUserAccounts(@PathVariable Long id) {
        UserAccounts userAccount = userAccountsService.deleteUserAccount(id);
        DeleteAccountResponse deleteAccountResponse = new DeleteAccountResponse();
        deleteAccountResponse.setAccountNumber(userAccount.getAccountNumber());
        deleteAccountResponse.setAccountName(userAccount.getAccountName().toString());

        return ResponseEntity.ok(deleteAccountResponse);
    }
}
