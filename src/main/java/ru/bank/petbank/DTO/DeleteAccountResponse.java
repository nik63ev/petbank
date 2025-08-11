package ru.bank.petbank.DTO;

import lombok.Data;

@Data
public class DeleteAccountResponse {
    private String accountName;
    String accountNumber;
}
