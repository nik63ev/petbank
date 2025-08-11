package ru.bank.petbank.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bank.petbank.DTO.CreateAccountRequest;
import ru.bank.petbank.DTO.CreateAccountResponse;
import ru.bank.petbank.DTO.Status;
import ru.bank.petbank.model.UserAccounts;
import ru.bank.petbank.model.UserInfo;
import ru.bank.petbank.repository.UserAccountsRepository;
import ru.bank.petbank.repository.UserInfoRepository;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class UserAccountsService {
    @Autowired
    private UserAccountsRepository userAccountsRepository;
    @Autowired
    private UserInfoRepository userInfoRepository;

    public Optional<List<UserAccounts>> getUserAccounts (Long userInfoId){
        UserInfo userInfo = userInfoRepository.getUserInfoById(userInfoId);
        Optional<List<UserAccounts>> userAccounts = userAccountsRepository.getAllUserAccountsByUserInfo(userInfo);
        userAccounts.orElseThrow(() -> new RuntimeException("Information about the user accounts is not found"));
        return userAccounts;
    }

    @Transactional
    public CreateAccountResponse createUserAccount(CreateAccountRequest request) {
        String accountNumber = null;
        do {
            accountNumber = "3333 0063" + createEndPartAccountNumber();
        } while (userAccountsRepository.existsByAccountNumber(accountNumber));

        Optional<UserInfo> userInfo = userInfoRepository.findUserInfoById(request.getUserinfoID());
        UserAccounts userAccount = new UserAccounts(request.getAccountname(), accountNumber, userInfo.get());
        System.out.println(userAccount);
        userAccountsRepository.save(userAccount);
        CreateAccountResponse response = new CreateAccountResponse();
        response.setStatus(new Status());
        response.getStatus().setMessage("Account created");
        response.getStatus().setCode(0);
        response.setUserInfoId(userInfo.get());
        response.setAccountNumber(userAccount.getAccountNumber());
        response.setAccountName(userAccount.getAccountName().toString());
        response.setBalance(userAccount.getBalance());
        return response;
    }

    @Transactional
    public UserAccounts getUserAccount (String accountNumber){
        Optional<UserAccounts> userAccounts = userAccountsRepository.findUserAccountsByAccountNumber(accountNumber);
        if (userAccounts.isPresent()){
            return userAccounts.get();
        }
        else return null;
    }

    @Transactional
    public UserAccounts deleteUserAccount(Long accountId){
        Optional<UserAccounts> userAccounts = userAccountsRepository.findUserAccountsById(accountId);
        if (userAccounts.isPresent()) {
            if (userAccounts.get().getBalance() != 0.0d){
                throw new RuntimeException("Account balance must be zero");
            } else { userAccountsRepository.delete(userAccounts.get());}
        }
        else {
            throw new RuntimeException("Information about the user accounts is not found");
        }
        return userAccounts.get();
    }

    private String createEndPartAccountNumber(){
        StringBuilder endPartAccountNumber = new StringBuilder();
        Random rand = new Random();
        for (int i = 0; i < 2; i++) {
            int randomNum = 1000 + rand.nextInt(9000);
            endPartAccountNumber.append(" ");
            endPartAccountNumber.append(String.valueOf(randomNum));
        }
        return endPartAccountNumber.toString();
    }
}
