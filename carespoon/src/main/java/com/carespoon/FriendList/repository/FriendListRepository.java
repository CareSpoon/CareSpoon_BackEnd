package com.carespoon.FriendList.repository;

import com.carespoon.FriendList.domain.FriendList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FriendListRepository extends JpaRepository<FriendList, Long> {
    FriendList findByListId(Long Id);
}
