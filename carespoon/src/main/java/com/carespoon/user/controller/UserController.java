package com.carespoon.user.controller;


import com.carespoon.user.domain.User;
import com.carespoon.user.dto.UserSaveRequestDto;
import com.carespoon.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    @PostMapping("/user")
    public String save(@RequestBody UserSaveRequestDto requestDto){
        User user = userService.save(requestDto.getEmail(), requestDto.getName(), requestDto.getRole());
        return user.getUuid();
    }
    @GetMapping("/user/{uuid}")
    public User findByUUID(@PathVariable String uuid){
        return userService.findByUuid(uuid);
    }

    @GetMapping("/user_id/{id}")
    public User findById(@PathVariable Long id){
        return userService.findById(id);
    }
}
