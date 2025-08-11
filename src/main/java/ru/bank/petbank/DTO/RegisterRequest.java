package ru.bank.petbank.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegisterRequest {
    @NotBlank
    private String username;
    private String password;
    private String email;
    private Long userInfoId;

}
