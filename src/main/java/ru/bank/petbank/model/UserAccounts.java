package ru.bank.petbank.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "\"user_accounts\"")
public class UserAccounts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private int id;

    @Column(name = "account_name")
    private String accountName;

    @Column(name = "account_number")
    private String accountNumber;

    @Column(name = "balance")
    private double balance;

    @Column(name = "limit")
    private double limit;

    @Column(name = "account_status")
    private String accountStatus;

    @Column(name = "user_info_id")
    private Long userInfoId;

    public UserAccounts(AccountName accountName, String endPartAccountNumber, Long userInfoId) {
        this.accountName = accountName.toString();
        this.accountNumber = "3333 0063 " + endPartAccountNumber;
        this.balance = 0.0d;
        this.accountStatus = AccountStatus.UNLOCKED.toString();
        this.userInfoId = userInfoId;
    }
    public UserAccounts() {}

}
