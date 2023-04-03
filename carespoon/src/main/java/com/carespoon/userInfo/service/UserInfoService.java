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
