package com.carespoon.friendList.service;

import com.carespoon.friendList.repository.FriendListRepository;
import com.carespoon.friendList.domain.FriendList;
import com.carespoon.friendList.dto.FriendListSaveDto;
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
        return friendListRepository.save(friendListSaveDto.toEntity()).getListId();
    }

    @Transactional
    public void deleteFriend(Long listId){
        FriendList friendList = friendListRepository.findByListId(listId);
        friendListRepository.delete(friendList);
    }
}
