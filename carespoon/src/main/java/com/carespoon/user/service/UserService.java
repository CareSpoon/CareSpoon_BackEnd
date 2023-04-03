package com.carespoon.user.service;

import com.carespoon.user.repository.UserRepository;
import com.carespoon.user.domain.User;
import com.carespoon.user.dto.UserSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;


    public void save(UserSaveRequestDto requestDto){
        userRepository.save(requestDto.toEntity());
    }
    public User findByUuid(UUID uuid) {
        User user = userRepository.findByUuid(uuid);
        return user;
    }
}
