package ru.bank.petbank.DTO;

import lombok.Data;

@Data
public class CreateInternalTransferDraftRequest {
    private String sender_account_number;
    private Long senderID;
}
