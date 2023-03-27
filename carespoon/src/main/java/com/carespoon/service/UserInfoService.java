package com.carespoon.service;

import com.carespoon.Repository.UserInfoRepository;
import com.carespoon.domain.User;
import com.carespoon.domain.UserInfo;
import com.carespoon.dto.UserInfoResponseDto;
import com.carespoon.dto.UserInfoSaveRequestDto;
import com.carespoon.dto.UserInfoUpdateRequestDto;
import javax.transaction.Transactional;
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
