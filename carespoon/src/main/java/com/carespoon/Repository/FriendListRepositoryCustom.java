package com.carespoon.Repository;

import com.querydsl.core.Tuple;

import java.util.List;
import java.util.UUID;

public interface FriendListRepositoryCustom {
    List<Tuple> findBySeniorId(UUID uuid);
    List<Tuple> findByViewerId(UUID uuid);

    Long findIdByUUID(UUID vieweruuid, UUID senioruuid);
}
