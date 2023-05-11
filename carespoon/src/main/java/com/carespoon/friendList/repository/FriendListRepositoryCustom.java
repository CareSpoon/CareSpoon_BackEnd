package com.carespoon.friendList.repository;

import com.carespoon.friendList.dto.FriendListGetResponseDto;

import java.util.List;

public interface FriendListRepositoryCustom {
    List<FriendListGetResponseDto> findBySeniorId(String uuid);
    List<FriendListGetResponseDto> findByViewerId(String uuid);

    List<Long> findIdByUUID(String vieweruuid, String senioruuid);
}
