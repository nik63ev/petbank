package ru.bank.petbank.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import ru.bank.petbank.model.Gender;

@Data
public class CreateInfoRequest {
    @NotBlank
    String surname;
    String name;
    String lastname;
    String dateofbirth;
    Gender gender;
    String phone;
    String email;
}
