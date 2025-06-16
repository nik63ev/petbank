package ru.bank.petbank.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Generated;
import lombok.ToString;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Data
@Entity
@Table(name = "\"user_information\"")
//@Builder
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_information_id")
    private Long id;

    @Column(name = "surname", nullable = false, length = 100)
    private String surName;
    @Column(name = "name", nullable = false, length = 100)
    private String name;
    @Column(name = "lastname", nullable = false, length = 100)
    private String lastName;
    @Column(name = "date_of_birth", nullable = false)
    private String dateOfBirth; //(dd.MM.yyyy)

//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "user_credential_id")
//    private UserCredential userCredential;

    @Column(name = "age")
    private Integer age;

    @Column(name = "gender", nullable = false)
    private String gender;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "user_info_id", unique = true, nullable = false)
    private Long userInfoId;

    public UserInfo(String surName, String name, String lastName, String dateOfBirth, Gender gender
                    , String phone, String email) {
        this.surName = surName;
        this.name = name;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.age = solveAge(dateOfBirth);
        this.gender = gender.toString();
        this.phone = phone;
        this.email = email;
        this.userInfoId = UUID.randomUUID().getMostSignificantBits();
    }

    private Integer solveAge(String dateOfBirth) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate today = LocalDate.now();
        today = LocalDate.parse(today.format(formatter), formatter);
        LocalDate birthday = LocalDate.parse(dateOfBirth, formatter);
        Period period = Period.between(birthday, today);
        return period.getYears();
    }

    public UserInfo() {
    }
}
