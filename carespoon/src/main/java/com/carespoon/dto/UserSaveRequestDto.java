package com.carespoon.dto;

import com.carespoon.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
public class UserSaveRequestDto {
    private String email;

    private String name;

    private UUID uuid;

    private int role;

    @Builder
    public UserSaveRequestDto(String email, String name, int role){
        this.email = email;
        this.name = name;
        this.role = role;
        this.uuid = UUID.randomUUID();
    }
    public User toEntity() {
        return User.builder()
                .email(email)
                .name(name)
                .role(role)
                .build();
    }
}
