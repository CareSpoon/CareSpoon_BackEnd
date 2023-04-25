package com.carespoon.user.service;

import com.carespoon.user.dto.UserSaveRequestDto;
import com.carespoon.user.repository.UserRepository;
import com.carespoon.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User save(String email, String name, String role){
        UserSaveRequestDto requestDto = new UserSaveRequestDto(email, name, role);
        return userRepository.save(requestDto.toEntity());
    }
    public User findByUuid(UUID uuid) {
        User user = userRepository.findByUuid(uuid);
        return user;
    }
}
