package com.carespoon.service;

import com.carespoon.Repository.FriendListRepository;
import com.carespoon.domain.FriendList;
import com.carespoon.dto.FriendListSaveDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class FriendListService {

    @Autowired
    private FriendListRepository friendListRepository;

    @Transactional
    public Long save(FriendListSaveDto friendListSaveDto){
        return friendListRepository.save(friendListSaveDto.toEntity()).getId();
    }

    @Transactional
    public void deleteFriend(Long listId){
        FriendList friendList = friendListRepository.findByListId(listId);
        friendListRepository.delete(friendList);
    }
}
