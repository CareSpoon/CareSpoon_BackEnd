package com.carespoon.oauth.dto;

import com.carespoon.user.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
public class GoogleProfile {
    private String email;
    private String name;
    private UUID uuid;
    private int role;

    public User toEntity() {
        User user = User.builder()
                .name(this.name)
                .email(this.email)
                .role(this.role)
                .build();
        return user;
    }
}
