package ru.bank.petbank.controller;

import lombok.Data;

@Data
public class CreateAccountResponse extends CommonResponse{
    private Long userInfoId;
    private String accountName;
    String accountNumber;
    private double balance;
}
