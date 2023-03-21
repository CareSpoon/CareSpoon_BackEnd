package com.carespoon.service;

import com.carespoon.Repository.FriendListRepository;
import com.carespoon.dto.FriendListSaveDto;
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

    public Long save(FriendListSaveDto friendListSaveDto){
        return friendListRepository.save(friendListSaveDto.toEntity()).getId();
    }
    public List<String> findFriendList(UUID uuid){
        List<String> friendList = friendListRepository.findBySeniorId(uuid);
        return friendList;
    }
}
