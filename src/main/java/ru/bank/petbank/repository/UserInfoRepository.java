package ru.bank.petbank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.bank.petbank.model.UserInfo;

import java.util.Optional;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {
    Optional<UserInfo> findByUserInfoId(Long userInfoId);
    boolean existsByPhone(String phone);

    UserInfo getUserInfoByUserInfoId(Long userInfoId);
}
