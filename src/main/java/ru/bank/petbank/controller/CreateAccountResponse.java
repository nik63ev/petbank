package ru.bank.petbank.controller;

import lombok.Data;
import ru.bank.petbank.model.UserInfo;

@Data
public class CreateAccountResponse extends CommonResponse{
    private UserInfo userInfoId;
    private String accountName;
    String accountNumber;
    private Double balance;
}
