package com.carespoon.friendList.dto;

import com.carespoon.friendList.domain.FriendList;

import java.util.UUID;

public class FriendListResponseDto {
    private Long id;

    private UUID viewerId;

    private String viewerName;

    private UUID seniorId;

    private String seniorName;

    public FriendListResponseDto(FriendList entity){
        this.id = entity.getListId();
        this.viewerId = entity.getViewerId();
        this.viewerName = entity.getViewerName();
        this.seniorId = entity.getSeniorId();
        this.seniorName = entity.getSeniorName();
    }

}
