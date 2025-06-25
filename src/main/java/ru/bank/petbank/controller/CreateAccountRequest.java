package ru.bank.petbank.controller;

import lombok.Data;
import ru.bank.petbank.model.AccountName;

@Data
public class CreateAccountRequest {
    private Long userinfoID;
    private AccountName accountname;
}
