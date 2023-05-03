package com.carespoon.friendList.repository;

import com.querydsl.core.Tuple;

import java.util.List;
import java.util.UUID;

public interface FriendListRepositoryCustom {
    List<Tuple> findBySeniorId(String uuid);
    List<Tuple> findByViewerId(String uuid);

    Long findIdByUUID(String vieweruuid, String senioruuid);
}
