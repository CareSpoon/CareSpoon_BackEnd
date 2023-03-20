package com.carespoon.dto;

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
        this.seniorName = userService.findNameByUUId(seniorId);
        this.viewerName = userService.findNameByUUId(viewerId);
    }
}
