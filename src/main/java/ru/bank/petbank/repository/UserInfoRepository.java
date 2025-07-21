package ru.bank.petbank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.bank.petbank.model.UserInfo;

import java.util.Optional;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, String> {
    Optional<UserInfo> findUserInfoById(Long id);
    boolean existsByPhone(String phone);

    UserInfo getUserInfoById(Long id);
}
