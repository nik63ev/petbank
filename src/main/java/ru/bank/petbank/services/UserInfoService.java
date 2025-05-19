package ru.bank.petbank.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bank.petbank.controller.CreateInfoRequest;
import ru.bank.petbank.model.UserInfo;
import ru.bank.petbank.repository.UserInfoRepository;

import java.util.Optional;

@Service
public class UserInfoService {

    private final UserInfoRepository userInfoRepository;

    @Autowired
    public UserInfoService(UserInfoRepository userInfoRepository) {
        this.userInfoRepository = userInfoRepository;
    }

    @Transactional
    public UserInfo getUserInfo(Long userInfoId) {
        return userInfoRepository.findByUserInfoId(userInfoId)
                .orElseThrow(() -> new RuntimeException("Information about the user is not found"));
    }

    @Transactional
    public UserInfo createUserInfo(CreateInfoRequest userInfo) {
        if (userInfoRepository.existsByPhone(userInfo.getPhone())) {
            throw new RuntimeException("Phone is already use");
        }
        UserInfo userInfoSaved = new UserInfo(userInfo.getSurname(), userInfo.getName(), userInfo.getLastname()
                                            , userInfo.getDateofbirth(), userInfo.getGender(), userInfo.getPhone()
                                            , userInfo.getEmail());
        System.out.println(userInfoSaved.getAge());
        return userInfoRepository.save(userInfoSaved);
    }

    @Transactional
    public UserInfo deleteUserInfo(Long userInfoId) {
        Optional<UserInfo> userInfoOptional = userInfoRepository.findByUserInfoId(userInfoId);
        if (userInfoOptional.isPresent()) {
            userInfoRepository.delete(userInfoOptional.get());
        }

        return userInfoOptional.get();
    }
}
