package ru.bank.petbank.DTO;

import lombok.Data;

@Data
public class SessionResponse extends CommonResponse{
    Long sessionToken;
    Long userId;
}
