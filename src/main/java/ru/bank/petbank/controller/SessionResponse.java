package ru.bank.petbank.controller;

import lombok.Data;

@Data
public class SessionResponse extends CommonResponse{
    Long sessionToken;
    Long userId;
}
