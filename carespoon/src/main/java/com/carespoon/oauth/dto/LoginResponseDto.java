package com.carespoon.oauth.dto;

import lombok.Getter;

import java.util.UUID;

@Getter
public class LoginResponseDto {
    private String accessToken;
    private UUID uuid;

    public LoginResponseDto(String accessToken, UUID uuid){
        this.accessToken = accessToken;
        this.uuid = uuid;
    }
}
