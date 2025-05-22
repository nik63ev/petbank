package ru.bank.petbank.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bank.petbank.controller.CreateInfoRequest;
import ru.bank.petbank.controller.CreateInfoResponse;
import ru.bank.petbank.controller.Status;
import ru.bank.petbank.model.Gender;
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
    public CreateInfoResponse createUserInfo(CreateInfoRequest userInfo) {
        if (userInfoRepository.existsByPhone(userInfo.getPhone())) {
            throw new RuntimeException("Phone is already use");
        }
        UserInfo userInfoSaved = new UserInfo(userInfo.getSurname(), userInfo.getName(), userInfo.getLastname()
                                            , userInfo.getDateofbirth(), userInfo.getGender(), userInfo.getPhone()
                                            , userInfo.getEmail());

        userInfoRepository.save(userInfoSaved);
        CreateInfoResponse createInfoResponse = new CreateInfoResponse();
        createInfoResponse.setStatus(new Status());
        createInfoResponse.getStatus().setMessage("User info is successfully created");
        createInfoResponse.getStatus().setCode(0);
        createInfoResponse.setUserInfoId(userInfoSaved.getUserInfoId());
        return createInfoResponse;
    }

    @Transactional
    public CreateInfoResponse updateUserInfo(Long userInfoId, CreateInfoRequest userInfo) {
        UserInfo updateUserInfo = userInfoRepository.getUserInfoByUserInfoId(userInfoId);
        if (updateUserInfo == null) {
            throw new RuntimeException("Information about the user is not found");
        }
        updateUserInfo.setSurName(userInfo.getSurname());
        updateUserInfo.setSurName(userInfo.getName());
        updateUserInfo.setLastName(userInfo.getLastname());
        updateUserInfo.setPhone(userInfo.getPhone());
        updateUserInfo.setEmail(userInfo.getEmail());
        userInfoRepository.save(updateUserInfo);
        CreateInfoResponse createInfoResponse = new CreateInfoResponse();
        createInfoResponse.setStatus(new Status());
        createInfoResponse.getStatus().setMessage("User info is successfully updated");
        createInfoResponse.getStatus().setCode(0);
        createInfoResponse.setUserInfoId(updateUserInfo.getUserInfoId());
        return createInfoResponse;
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
