package ru.bank.petbank.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "\"user_accounts\"")
public class UserAccounts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "account_name", nullable = false)
    @Enumerated(EnumType.STRING)
    private AccountName accountName;

    @Column(name = "account_number", nullable = false)
    private String accountNumber;

    @Column(name = "balance", nullable = false)
    private Double balance;

    @Column(name = "account_limit", nullable = true)
    private Double account_limit;

    @Column(name = "account_status")
    @Enumerated(EnumType.STRING)
    private AccountStatus accountStatus;

    @Column(name = "account_status_change_date")
    private Date accountStatusChangeDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_info_id", nullable = false)
    private UserInfo userInfo;

    public UserAccounts(AccountName accountName, String accountNumber, UserInfo userInfo) {
        this.accountName = accountName;
        this.accountNumber = accountNumber;
        this.balance = 0.0d;
        this.account_limit = 0.0d;
        this.accountStatus = AccountStatus.UNLOCKED;
        this.accountStatusChangeDate = new Date();
        this.userInfo = userInfo;
    }
    public UserAccounts() {}

}
