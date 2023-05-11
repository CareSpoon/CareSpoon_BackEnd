package com.carespoon.friendList.repository;

import com.carespoon.friendList.domain.FriendList;
import com.carespoon.friendList.domain.QFriendList;
import com.carespoon.friendList.dto.FriendListGetResponseDto;
import com.carespoon.friendList.dto.FriendListResponseDto;
import com.carespoon.friendList.dto.QFriendListGetResponseDto;
import com.carespoon.oneMeal.domain.OneMeal;
import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FriendListRepositoryImpl extends QuerydslRepositorySupport implements FriendListRepositoryCustom {
    public FriendListRepositoryImpl(JPAQueryFactory queryFactory){
        super(OneMeal.class);
        this.queryFactory = queryFactory;
    }

    private final JPAQueryFactory queryFactory;
    @Override
    public List<FriendListGetResponseDto> findBySeniorId(String uuid) {
        QFriendList friendList = QFriendList.friendList;
        QueryResults<FriendListGetResponseDto> results =
                queryFactory.from(friendList)
                .select(new QFriendListGetResponseDto(friendList.viewerId, friendList.viewerName))
                .where(friendList.seniorId.eq(uuid))
                .fetchResults();
        List<FriendListGetResponseDto> responseDtos = results.getResults();
        return responseDtos;
    }

    @Override
    public List<FriendListGetResponseDto> findByViewerId(String uuid) {
        QFriendList friendList = QFriendList.friendList;
        QueryResults<FriendListGetResponseDto> results =
                queryFactory.from(friendList)
                        .select(new QFriendListGetResponseDto(friendList.seniorId, friendList.seniorName))
                        .where(friendList.viewerId.eq(uuid))
                        .fetchResults();
        List<FriendListGetResponseDto> responseDtos = results.getResults();
        return responseDtos;
    }

    @Override
    public List<Long> findIdByUUID(String senioruuid,String vieweruuid) {
        QFriendList friendList = QFriendList.friendList;
        return from(friendList)
                .select(friendList.listId)
                .where(friendList.seniorId.eq(senioruuid).and(friendList.viewerId.eq(vieweruuid)))
                .fetch();
    }
}
