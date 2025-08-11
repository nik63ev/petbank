package ru.bank.petbank.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AuthenticationRequest {
    @NotBlank
    private String username;
    private String password;
}
