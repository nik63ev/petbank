package ru.bank.petbank.controller;

import lombok.Data;

@Data
public class DeleteAccountResponse {
    private String accountName;
    String accountNumber;
}
