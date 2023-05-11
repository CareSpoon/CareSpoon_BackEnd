package com.carespoon.userInfo.controller;

import com.carespoon.userInfo.domain.UserInfo;
import com.carespoon.userInfo.dto.UserInfoResponseDto;
import com.carespoon.userInfo.dto.UserInfoSaveRequestDto;
import com.carespoon.userInfo.dto.UserInfoUpdateRequestDto;
import com.carespoon.userInfo.dto.UserRequestDto;
import com.carespoon.userInfo.service.UserInfoService;
import com.carespoon.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
public class UserInfoController {

    private final UserInfoService userInfoService;

    private UserService userService;

    @PostMapping("/userinfo")
    public UserInfo save(@RequestBody UserRequestDto userInfoSaveRequestDto){
        return userInfoService.save(userInfoSaveRequestDto);
    }

    @GetMapping("/userinfo/{userId}")
    public UserInfoResponseDto findByUser(@PathVariable String userId){
        return userInfoService.findByUser(userId);
    }

    @PutMapping("/userinfo/update/{userId}")
    public void update(@PathVariable String userId, @RequestBody UserInfoUpdateRequestDto userInfoUpdateRequestDto){
        userInfoService.update(userId, userInfoUpdateRequestDto);
    }
}
