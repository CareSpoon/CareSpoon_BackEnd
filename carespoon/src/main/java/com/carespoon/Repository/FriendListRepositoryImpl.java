package com.carespoon.Repository;

import com.carespoon.domain.FriendList;
import com.carespoon.domain.QFriendList;
import com.querydsl.core.Tuple;
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
    public List<Tuple> findBySeniorId(UUID uuid){
        QFriendList friendList = QFriendList.friendList;

        return from(friendList)
                .select(friendList.viewerName, friendList.viewerId)
                .where(friendList.seniorId.eq(uuid))
                .fetch();

    }

    @Override
    public List<Tuple> findByViewerId(UUID uuid){
        QFriendList friendList = QFriendList.friendList;
        return from(friendList)
                .select(friendList.seniorName, friendList.seniorId)
                .where(friendList.viewerId.eq(uuid))
                .fetch();
    }
}
