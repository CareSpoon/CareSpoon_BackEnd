package com.carespoon.Repository;

import com.carespoon.domain.FriendList;
import com.carespoon.domain.QFriendList;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class FriendListRepositoryImpl extends QuerydslRepositorySupport implements FriendListRepositoryCustom {
    public FriendListRepositoryImpl(){
        super(FriendList.class);
    }

    @Override
    public List<String> findBySeniorId(UUID uuid){
        QFriendList friendList = QFriendList.friendList;

        return from(friendList)
                .select(friendList.viewerName)
                .where(friendList.seniorId.eq(uuid))
                .fetch();

    }

    @Override
    public List<String> findByViewerId(UUID uuid){
        QFriendList friendList = QFriendList.friendList;
        return from(friendList)
                .select(friendList.seniorName)
                .where(friendList.viewerId.eq(uuid))
                .fetch();
    }
}
