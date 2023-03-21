package com.carespoon.Repository;

import com.carespoon.domain.FriendList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface FriendListRepository extends JpaRepository<FriendList, Long> {
    List<String> findBySeniorId(UUID uuid);
    List<String> findByViewerId(UUID uuid);
}
