package com.carespoon.friendList.service;

import com.carespoon.friendList.repository.FriendListRepository;
import com.carespoon.friendList.domain.FriendList;
import com.carespoon.friendList.dto.FriendListSaveDto;
import com.carespoon.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class FriendListService {

    @Autowired
    private final FriendListRepository friendListRepository;

    @Autowired
    private final UserRepository userRepository;

    @Transactional
    public void save(FriendListSaveDto friendListSaveDto){
        String seniorId = friendListSaveDto.getSeniorId();
        String viewerId = friendListSaveDto.getViewerId();
        String seniorName= userRepository.findUserByUuid(friendListSaveDto.getSeniorId()).getName();
        String viewerName = userRepository.findUserByUuid(friendListSaveDto.getViewerId()).getName();
        FriendList friendList = new FriendList(viewerId, seniorId, seniorName,viewerName);
        friendListRepository.save(friendList);
    }

    @Transactional
    public void deleteFriend(Long listId){
        FriendList friendList = friendListRepository.findByListId(listId);
        friendListRepository.delete(friendList);
    }
}
