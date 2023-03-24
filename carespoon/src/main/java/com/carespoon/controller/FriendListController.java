package com.carespoon.controller;

import com.carespoon.Repository.FriendListRepositoryCustom;
import com.carespoon.dto.FriendListSaveDto;
import com.carespoon.service.FriendListService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
public class FriendListController {

    private FriendListService friendListService;

    @Autowired
    @Qualifier("friendListRepositoryImpl")
    private FriendListRepositoryCustom friendListRepositoryCustom;
    @PostMapping("/friendlist")
    public Long save(@RequestBody FriendListSaveDto friendListSaveDto){
        return friendListService.save(friendListSaveDto);
    }

    @GetMapping("/friendsof")
    public List<String> getFriend(@RequestParam UUID uuid, @RequestParam int role){
        List<String> friendsOf;
        if(role == 0){
            friendsOf = friendListRepositoryCustom.findBySeniorId(uuid);
        }else{
            friendsOf = friendListRepositoryCustom.findByViewerId(uuid);
        }
        return friendsOf;
    }
}
