package com.carespoon.service;

import com.carespoon.Repository.FriendListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.List;

@RequiredArgsConstructor
@Service
public class FriendListService {

    @Autowired
    private FriendListRepository friendListRepository;

    public List<UUID> findFriendList(UUID uuid){
        List<UUID> friendList = friendListRepository.findMyFriendList(uuid);
        return friendList;
    }
}
