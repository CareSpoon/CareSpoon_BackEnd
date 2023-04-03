package com.carespoon.service;

import com.carespoon.FriendList.repository.FriendListRepository;
import com.carespoon.FriendList.domain.FriendList;
import com.carespoon.FriendList.dto.FriendListSaveDto;
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
