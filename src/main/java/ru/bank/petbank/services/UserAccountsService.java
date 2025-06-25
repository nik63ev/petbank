package ru.bank.petbank.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bank.petbank.controller.CreateAccountRequest;
import ru.bank.petbank.controller.CreateAccountResponse;
import ru.bank.petbank.controller.Status;
import ru.bank.petbank.model.UserAccounts;
import ru.bank.petbank.repository.UserAccountsRepository;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class UserAccountsService {
    @Autowired
    private final UserAccountsRepository userAccountsRepository;

    UserAccountsService(final UserAccountsRepository userAccountsRepository) {
        this.userAccountsRepository = userAccountsRepository;
    }

    public Optional<List<UserAccounts>> getUserAccounts (Long userInfoId){
        Optional<List<UserAccounts>> userAccounts = userAccountsRepository.getAllUserAccountsByUserInfoId(userInfoId);
        userAccounts.orElseThrow(() -> new RuntimeException("Information about the user accounts is not found"));
        return userAccounts;
    }

    @Transactional
    public CreateAccountResponse createUserAccount(CreateAccountRequest request) {
        String accountNumber = null;
        do {
            accountNumber = "3333 0063" + createEndPartAccountNumber();
        } while (userAccountsRepository.existsByAccountNumber(accountNumber));

        UserAccounts userAccount = new UserAccounts(request.getAccountname(), accountNumber, request.getUserinfoID());
        System.out.println(userAccount);
        userAccountsRepository.save(userAccount);
        CreateAccountResponse response = new CreateAccountResponse();
        response.setStatus(new Status());
        response.getStatus().setMessage("Account created");
        response.getStatus().setCode(0);
        response.setUserInfoId(userAccount.getUserInfoId());
        response.setAccountNumber(userAccount.getAccountNumber());
        response.setAccountName(userAccount.getAccountName());
        response.setBalance(userAccount.getBalance());
        return response;
    }

    @Transactional
    public UserAccounts getUserAccount (String accountNumber){
        return userAccountsRepository.findUserAccountsByAccountNumber(accountNumber)
                .orElseThrow(() -> new RuntimeException("Information about the user accounts is not found"));
    }

    @Transactional
    public UserAccounts deleteUserAccount(String accountNumber){
        Optional<UserAccounts> userAccounts = userAccountsRepository.findUserAccountsByAccountNumber(accountNumber);
        if (userAccounts.isPresent()) {
            userAccountsRepository.delete(userAccounts.get());
        }
        else {
            throw new RuntimeException("Information about the user accounts is not found");
        }
        return userAccounts.get();
    }

    private String createEndPartAccountNumber(){
        String endPartAccountNumber = "";
        Random rand = new Random();
        for (int i = 0; i < 2; i++) {
            int randomNum = 1000 + rand.nextInt(9000);
            endPartAccountNumber = endPartAccountNumber + " " + String.valueOf(randomNum);
        }
        return endPartAccountNumber;
    }
}
