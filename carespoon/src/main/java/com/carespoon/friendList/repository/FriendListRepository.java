package com.carespoon.friendList.repository;

import com.carespoon.friendList.domain.FriendList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FriendListRepository extends JpaRepository<FriendList, Long> {
    FriendList findByListId(Long Id);
}
