package com.carespoon.controller;

import com.carespoon.domain.TokenRequest;
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
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody TokenRequest tokenRequest) {
        String idToken = tokenRequest.getIdToken();
        // TODO: ID 토큰 인증 및 사용자 정보 추출, 필요시 refresh 토큰 발급 및 저장 등 구현
        return ResponseEntity.ok("Authentication success!");
    }

}
