package ru.bank.petbank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.bank.petbank.model.UserAccounts;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserAccountsRepository extends JpaRepository<UserAccounts, String> {
    boolean existsByAccountNumber(String accountNumber);
    boolean existsByUserInfoId(Long userInfoId);
    Optional<UserAccounts> findUserAccountsByAccountNumber(String accountNumber);
    Optional<UserAccounts> findByUserInfoId(Long userInfoId);

    Optional<List<UserAccounts>> getAllUserAccountsByUserInfoId(Long userInfoId);
}
