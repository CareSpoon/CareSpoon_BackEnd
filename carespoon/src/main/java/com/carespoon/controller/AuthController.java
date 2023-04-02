package com.carespoon.controller;

import com.carespoon.domain.TokenRequest;
import com.carespoon.dto.UserSaveRequestDto;
import com.carespoon.service.GoogleAuthService;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

import static org.springframework.security.oauth2.core.OAuth2TokenIntrospectionClaimNames.CLIENT_ID;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final GoogleAuthService googleAuthService;
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody String idToken, @RequestBody int role) throws IOException {
        UserSaveRequestDto userSaveRequestDto;
        try {
            GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), new JacksonFactory())
                    .setAudience(Collections.singletonList(CLIENT_ID))
                    .build();
            GoogleIdToken googleIdToken = verifier.verify(idToken);
            if (idToken != null) {
                GoogleIdToken.Payload payload = googleIdToken.getPayload();
                // ID 토큰에서 필요한 정보 추출
                String email = payload.getEmail();
                String name = (String) payload.get("name");
//                // 갱신된 ID 토큰 발급
//                String newIdToken = FirebaseUtil.refreshIdToken(idToken);
                // 사용자 정보 저장 및 회원가입 또는 로그인 등의 기능 수행
                userSaveRequestDto = new UserSaveRequestDto(email, name, role);
                return ResponseEntity.ok(userSaveRequestDto);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid ID token");
            }
        } catch (GeneralSecurityException | IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
