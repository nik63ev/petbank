package ru.bank.petbank.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Generated;
import lombok.ToString;

import java.time.LocalDate;
@Data
@Entity
@Table(name = "\"user_Information\"")
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
    private LocalDate dateOfBirth;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_credential_id")
    private UserCredential userCredential;

//    private Integer age;
//    private Gender gender;
//    private String phone;
//    private String email;

    public UserInfo(String surName, String name, String lastName, LocalDate dateOfBirth) {
        this.surName = surName;
        this.name = name;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString() {
        Long var10000 = this.getId();
        return "UserInfo(id=" + var10000 + ", surName=" + this.getSurName() + ", name=" + this.getName() + ", lastName=" + this.getLastName() + ", dateOfBirth=" + this.getDateOfBirth() + ")";
    }

    public UserInfo() {
    }

}
