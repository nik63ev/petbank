package ru.bank.petbank.DTO;

import lombok.Data;
import ru.bank.petbank.model.AccountName;

@Data
public class CreateAccountRequest {
    private Long userinfoID;
    private AccountName accountname;
}
