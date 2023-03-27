package com.carespoon.controller;

import com.carespoon.dto.UserInfoResponseDto;
import com.carespoon.dto.UserInfoSaveRequestDto;
import com.carespoon.dto.UserInfoUpdateRequestDto;
import com.carespoon.service.UserInfoService;
import com.carespoon.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
public class UserInfoController {

    private final UserInfoService userInfoService;

    private UserService userService;

    @PostMapping("/userinfo/{userId}")
    public Long save(@PathVariable UUID userId, @RequestBody UserInfoSaveRequestDto userInfoSaveRequestDto){

        return userInfoService.save(userInfoSaveRequestDto);
    }

    @GetMapping("/userinfo/{id}")
    public UserInfoResponseDto findById(@PathVariable Long id){
        return userInfoService.findById(id);
    }

    @PutMapping("/userinfo/update/{id}")
    public Long update(@PathVariable Long id, @RequestBody UserInfoUpdateRequestDto userInfoUpdateRequestDto){
        return userInfoService.update(id, userInfoUpdateRequestDto);
    }

}
