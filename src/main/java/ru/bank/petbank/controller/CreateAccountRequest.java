package ru.bank.petbank.controller;

import lombok.Data;
import ru.bank.petbank.model.AccountName;
import ru.bank.petbank.model.UserInfo;

@Data
public class CreateAccountRequest {
    private Long userinfoID;
    private AccountName accountname;
}
