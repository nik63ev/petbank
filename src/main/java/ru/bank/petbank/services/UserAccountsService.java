package ru.bank.petbank.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bank.petbank.model.UserAccounts;
import ru.bank.petbank.repository.UserAccountsRepository;

@Service
public class UserAccountsService {
    @Autowired
    private final UserAccountsRepository userAccountsRepository;

    UserAccountsService(final UserAccountsRepository userAccountsRepository) {
        this.userAccountsRepository = userAccountsRepository;
    }

    public UserAccounts getUserAccounts (Long userInfoId){
        return userAccountsRepository.getUserAccountsByUserInfoId(userInfoId)
                .orElseThrow(() -> new RuntimeException("Information about the user is not found"));
    }
}
