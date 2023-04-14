package com.carespoon.oauth.controller;

import com.carespoon.oauth.dto.GoogleProfile;
import com.carespoon.user.domain.User;
import com.carespoon.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class OauthController {
    UserService userService;

}
