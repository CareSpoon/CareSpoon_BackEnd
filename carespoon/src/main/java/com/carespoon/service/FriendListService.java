package com.carespoon.service;

import com.carespoon.Repository.FriendListRepository;
import com.carespoon.dto.FriendListSaveDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FriendListService {

    @Autowired
    private FriendListRepository friendListRepository;

    public Long save(FriendListSaveDto friendListSaveDto){
        return friendListRepository.save(friendListSaveDto.toEntity()).getId();
    }
}
