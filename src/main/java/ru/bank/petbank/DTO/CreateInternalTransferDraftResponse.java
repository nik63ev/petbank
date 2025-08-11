package ru.bank.petbank.DTO;

import lombok.Data;

@Data
public class CreateInternalTransferDraftResponse extends CommonResponse{
    private String transferID;
}
