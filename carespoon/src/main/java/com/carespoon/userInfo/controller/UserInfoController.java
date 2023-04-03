package com.carespoon.userInfo.controller;

import com.carespoon.userInfo.dto.UserInfoResponseDto;
import com.carespoon.userInfo.dto.UserInfoSaveRequestDto;
import com.carespoon.userInfo.dto.UserInfoUpdateRequestDto;
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
    public void save(@RequestBody UserInfoSaveRequestDto userInfoSaveRequestDto){
        userInfoService.save(userInfoSaveRequestDto);
    }

    @GetMapping("/userinfo/{userId}")
    public UserInfoResponseDto findByUser(@PathVariable UUID userId){
        return userInfoService.findByUser(userId);
    }

    @PutMapping("/userinfo/update/{userId}")
    public void update(@PathVariable UUID userId, @RequestBody UserInfoUpdateRequestDto userInfoUpdateRequestDto){
        userInfoService.update(userId, userInfoUpdateRequestDto);
    }
}
