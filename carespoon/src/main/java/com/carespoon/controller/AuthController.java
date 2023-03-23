package com.carespoon.controller;

import com.carespoon.service.GoogleAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final GoogleAuthService googleAuthService;

//    @PostMapping(value = "/google")
//    public ResponseEntity<AuthResponse> googleAuthRequest(@RequestBody AuthRequest authRequest) {
//        return ApiResponse.success(googleAuthService.login(authRequest)); // body에 appToken 반환(response code 200)
//    }
}
