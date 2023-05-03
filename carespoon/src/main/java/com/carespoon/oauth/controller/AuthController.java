package com.carespoon.oauth.controller;

import com.carespoon.oauth.service.OauthService;
import com.carespoon.user.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AuthController {

//    @Value("${spring.security.oauth2.client.registration.google.client-id}")
//    private String googleClientId;
//
//    OauthService oauthService;
//    UserService userService;
//
//    @GetMapping("/login/{registrationId}")
//    public String login(@RequestParam String serverAuthCode, @RequestParam String role, @RequestParam String name, @PathVariable String registrationId) {
//        userService.save(role, name, registrationId);
//        return oauthService.socialLogin(serverAuthCode, registrationId);
//    }
}
