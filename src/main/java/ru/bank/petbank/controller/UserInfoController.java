package ru.bank.petbank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.bank.petbank.model.UserInfo;
import ru.bank.petbank.services.UserInfoService;

@RestController
@RequestMapping("/api/info")
public class UserInfoController {

    private final UserInfoService userInfoService;

    @Autowired
    public UserInfoController(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    @GetMapping("/{userInfoId}")
    public ResponseEntity<UserInfo> getUserInfo(@PathVariable Long userInfoId) {
        UserInfo userInfo = userInfoService.getUserInfo(userInfoId);
        return ResponseEntity.ok(userInfo);
    }

    @PostMapping("/create")
    public ResponseEntity<CreateInfoResponse> createUserInfo(@Validated @RequestBody CreateInfoRequest createInfoDTO) {

        CreateInfoResponse createUserInfoResponse = userInfoService.createUserInfo(createInfoDTO);
        return ResponseEntity.ok(createUserInfoResponse);
    }

    @PutMapping("/{userInfoId}")
    public ResponseEntity<CreateInfoResponse> updateUserInfo(@PathVariable Long userInfoId
            , @Validated @RequestBody CreateInfoRequest createInfoDTO) {
        CreateInfoResponse createUserInfoResponse = userInfoService.updateUserInfo(userInfoId, createInfoDTO);
        return ResponseEntity.ok(createUserInfoResponse);
    }

    @DeleteMapping("/{userInfoId}")
    public ResponseEntity<UserInfo> deleteUserInfo(@PathVariable Long userInfoId) {
        UserInfo userInfo = userInfoService.getUserInfo(userInfoId);
        userInfoService.deleteUserInfo(userInfoId);
        return ResponseEntity.ok(userInfo);
    }

}
