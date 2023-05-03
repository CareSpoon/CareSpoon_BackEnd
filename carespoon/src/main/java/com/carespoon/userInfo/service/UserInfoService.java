package com.carespoon.userInfo.service;

import com.carespoon.userInfo.repository.UserInfoRepository;
import com.carespoon.user.domain.User;
import com.carespoon.userInfo.domain.UserInfo;
import com.carespoon.userInfo.dto.UserInfoResponseDto;
import com.carespoon.userInfo.dto.UserInfoSaveRequestDto;
import com.carespoon.userInfo.dto.UserInfoUpdateRequestDto;
import javax.transaction.Transactional;

import com.carespoon.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UserInfoService {

    private final UserInfoRepository userInfoRepository;

    private final UserService userService;
    @Transactional
    public UserInfo save(UserInfoSaveRequestDto userInfoSaveRequestDto){
        User user = userService.findByUuid(userInfoSaveRequestDto.getUserId());
        UserInfo userInfo = new UserInfo(user, userInfoSaveRequestDto.getAge(),userInfoSaveRequestDto.getSex(),userInfoSaveRequestDto.getHeight(), userInfoSaveRequestDto.getWeight());
        return userInfoRepository.save(userInfo);
    }

    @Transactional
    public void update(String uuid, UserInfoUpdateRequestDto userInfoUpdateRequestDto){
        User user = userService.findByUuid(uuid);
        UserInfo userInfo = userInfoRepository.findByUser(user);
        userInfo.update(userInfo.getUser(), userInfoUpdateRequestDto.getAge(), userInfoUpdateRequestDto.getSex(), userInfoUpdateRequestDto.getHeight(), userInfoUpdateRequestDto.getWeight());
    }

    public UserInfoResponseDto findByUser(String uuid){
        User user = userService.findByUuid(uuid);
        UserInfo entity = userInfoRepository.findByUser(user);
        return new UserInfoResponseDto(entity);
    }
}
