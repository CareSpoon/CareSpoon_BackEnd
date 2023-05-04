package com.carespoon.friendList.repository;

import com.carespoon.friendList.domain.FriendList;
import com.carespoon.friendList.domain.QFriendList;
import com.carespoon.friendList.dto.FriendListResponseDto;
import com.querydsl.core.Tuple;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class FriendListRepositoryImpl extends QuerydslRepositorySupport implements FriendListRepositoryCustom {
    public FriendListRepositoryImpl() {
        super(FriendList.class);
    }

    @Override
    public List<String> findBySeniorId(String uuid) {
        QFriendList friendList = QFriendList.friendList;
        return from(friendList)
                .select(friendList.viewerName)
                .where(friendList.seniorId.eq(uuid))
                .fetch();
    }

    @Override
    public List<String> findByViewerId(String uuid) {
        QFriendList friendList = QFriendList.friendList;

        return from(friendList)
                .select(friendList.seniorName)
                .where(friendList.viewerId.eq(uuid))
                .fetch();
    }

    @Override
    public Long findIdByUUID(String vieweruuid, String senioruuid) {
        QFriendList friendList = QFriendList.friendList;
        return from(friendList)
                .select(friendList.listId)
                .where(friendList.seniorId.eq(senioruuid).and(friendList.viewerId.eq(vieweruuid)))
                .fetch().get(0);
    }
}
