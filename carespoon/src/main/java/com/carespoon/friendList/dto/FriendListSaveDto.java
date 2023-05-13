package com.carespoon.friendList.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor
public class FriendListSaveDto {


    private String viewerId;

    private String seniorId;


    @Builder
    public FriendListSaveDto(String viewerId, String seniorId){
        this.viewerId = viewerId;
        this.seniorId = seniorId;
    }

}
