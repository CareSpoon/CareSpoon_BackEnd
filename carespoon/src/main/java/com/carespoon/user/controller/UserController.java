package com.carespoon.user.controller;


import com.carespoon.user.domain.User;
import com.carespoon.user.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class UserController {

    UserService userService;
    @GetMapping("/user/{uuid}")
    public User findByUUID(@PathVariable UUID uuid){
        return userService.findByUuid(uuid);
    }
}
