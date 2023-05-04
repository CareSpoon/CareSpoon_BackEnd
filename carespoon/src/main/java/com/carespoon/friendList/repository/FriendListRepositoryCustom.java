package com.carespoon.friendList.repository;

import com.carespoon.friendList.dto.FriendListResponseDto;
import com.querydsl.core.Tuple;

import java.util.List;
import java.util.UUID;

public interface FriendListRepositoryCustom {
    List<String> findBySeniorId(String uuid);
    List<String> findByViewerId(String uuid);

    List<Long> findIdByUUID(String vieweruuid, String senioruuid);
}
