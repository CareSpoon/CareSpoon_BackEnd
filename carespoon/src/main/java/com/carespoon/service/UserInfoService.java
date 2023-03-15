package com.carespoon.service;

import com.carespoon.Repository.UserInfoRepository;
import com.carespoon.domain.UserInfo;
import com.carespoon.dto.UserInfoResponseDto;
import com.carespoon.dto.UserInfoSaveRequestDto;
import com.carespoon.dto.UserInfoUpdateRequestDto;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserInfoService {
    private final UserInfoRepository userInfoRepository;

    @Transactional
    public Long save(UserInfoSaveRequestDto userInfoSaveRequestDto){
        return userInfoRepository.save(userInfoSaveRequestDto.toEntity()).getUserId();
    }

    @Transactional
    public Long update(Long id, UserInfoUpdateRequestDto userInfoUpdateRequestDto){
        UserInfo userInfo = userInfoRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("해당 회원 정보가 없습니다. id = " +id));
        userInfo.update(userInfo.getUserId(), userInfoUpdateRequestDto.getAge(), userInfoUpdateRequestDto.getSex(), userInfoUpdateRequestDto.getHeight(), userInfoUpdateRequestDto.getWeight());
        return id;
    }

    public UserInfoResponseDto findById(Long id){
        UserInfo entity = userInfoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 회원 정보가 없습니다. id = "+ id));
        return new UserInfoResponseDto(entity);
    }
}
