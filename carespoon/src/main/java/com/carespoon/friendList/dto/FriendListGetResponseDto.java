package com.carespoon.friendList.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
public class FriendListGetResponseDto {
    String uuid;
    String name;

    @QueryProjection
    public FriendListGetResponseDto(String uuid, String name){
        this.uuid = uuid;
        this.name = name;
    }
}
