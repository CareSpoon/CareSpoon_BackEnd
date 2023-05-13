package com.carespoon.friendList.controller;

import com.carespoon.friendList.dto.FriendListGetResponseDto;
import com.carespoon.friendList.repository.FriendListRepositoryCustom;
import com.carespoon.friendList.dto.FriendListSaveDto;
import com.carespoon.friendList.service.FriendListService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
public class FriendListController {

    private final FriendListService friendListService;

    @Autowired
    @Qualifier("friendListRepositoryImpl")
    private final FriendListRepositoryCustom friendListRepositoryCustom;
    @RequestMapping(value = "/friendlist" , method = RequestMethod.POST, produces = "application/json")
    public void save(@RequestBody FriendListSaveDto friendListSaveDto){
        friendListService.save(friendListSaveDto);
    }

    @GetMapping("/friendsof/senior/{uuid}")
    public List<FriendListGetResponseDto> getSeniorFriend(@PathVariable String uuid){
        return friendListRepositoryCustom.findBySeniorId(uuid);
    }

    @GetMapping("/friendsof/viewer/{uuid}")
    public List<FriendListGetResponseDto> getViewerFriend(@PathVariable String uuid){
        return friendListRepositoryCustom.findByViewerId(uuid);
    }

    @DeleteMapping("/friendlist/remove/{seniorId}/{viewerId}")
    public void friendRemove(@PathVariable String seniorId, @PathVariable String viewerId){
        List<Long> id = friendListRepositoryCustom.findIdByUUID(seniorId, viewerId);
        friendListService.deleteFriend(id.get(0));
    }
}
