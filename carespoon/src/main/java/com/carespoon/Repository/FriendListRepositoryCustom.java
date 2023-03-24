package com.carespoon.Repository;

import java.util.List;
import java.util.UUID;

public interface FriendListRepositoryCustom {
    List<String> findBySeniorId(UUID uuid);
    List<String> findByViewerId(UUID uuid);
}
