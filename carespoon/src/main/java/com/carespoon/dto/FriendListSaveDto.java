package com.carespoon.dto;

import com.carespoon.domain.FriendList;
import com.carespoon.service.UserService;

import java.util.UUID;

public class FriendListSaveDto {

    private Long id;

    private UUID viewerId;

    private String viewerName;

    private UUID seniorId;


    private String seniorName;

    private UserService userService;
    public FriendListSaveDto(UUID viewerId, UUID seniorId){
        this.viewerId = viewerId;
        this.seniorId = seniorId;
        this.seniorName = userService.findByUuid(seniorId);
        this.viewerName = userService.findByUuid(viewerId);
    }

    public FriendList toEntity(){
        return FriendList.builder()
                .viewerId(viewerId)
                .seniorId(seniorId)
                .seniorName(seniorName)
                .viewerName(viewerName)
                .build();
    }
}
