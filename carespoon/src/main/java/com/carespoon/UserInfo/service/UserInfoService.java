package com.carespoon.UserInfo.service;

import com.carespoon.UserInfo.repository.UserInfoRepository;
import com.carespoon.user.domain.User;
import com.carespoon.UserInfo.domain.UserInfo;
import com.carespoon.UserInfo.dto.UserInfoResponseDto;
import com.carespoon.UserInfo.dto.UserInfoSaveRequestDto;
import com.carespoon.UserInfo.dto.UserInfoUpdateRequestDto;
import javax.transaction.Transactional;

import com.carespoon.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UserInfoService {
    private final UserInfoRepository userInfoRepository;

    private UserService userService;
    @Transactional
    public void save(UserInfoSaveRequestDto userInfoSaveRequestDto){
        userInfoRepository.save(userInfoSaveRequestDto.toEntity());
    }

    @Transactional
    public void update(UUID uuid, UserInfoUpdateRequestDto userInfoUpdateRequestDto){
        User user = userService.findByUuid(uuid);
        UserInfo userInfo = userInfoRepository.findByUser(user);
        userInfo.update(userInfo.getUser(), userInfoUpdateRequestDto.getAge(), userInfoUpdateRequestDto.getSex(), userInfoUpdateRequestDto.getHeight(), userInfoUpdateRequestDto.getWeight());
    }

    public UserInfoResponseDto findByUser(UUID userId){
        User user = userService.findByUuid(userId);
        UserInfo entity = userInfoRepository.findByUser(user);
        return new UserInfoResponseDto(entity);
    }
}
