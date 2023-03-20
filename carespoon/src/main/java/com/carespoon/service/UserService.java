package com.carespoon.service;

import com.carespoon.Repository.UserRepository;
import com.carespoon.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public String findNameByUUId(java.util.UUID uuid){
        User user = userRepository.findByUUID(uuid);
        String name = user.getName();
        return name;
    }
}
