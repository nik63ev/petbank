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

    @GetMapping("/{id}")
    public ResponseEntity<UserInfo> getUserInfo(@PathVariable Long userInfoId) {
        UserInfo userInfo = userInfoService.getUserInfo(userInfoId);
        return ResponseEntity.ok(userInfo);
    }

    @PostMapping("/create")
    public ResponseEntity<UserInfo> createUserInfo(@Validated @RequestBody CreateInfoRequest createInfoDTO) {

        UserInfo createUserInfo = userInfoService.createUserInfo(createInfoDTO);
        System.out.println(createUserInfo);
        return ResponseEntity.ok(createUserInfo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserInfo(@PathVariable Long userInfoId) {
        UserInfo userInfo = userInfoService.getUserInfo(userInfoId);
        userInfoService.deleteUserInfo(userInfoId);
        return ResponseEntity.noContent().build();
    }

}
