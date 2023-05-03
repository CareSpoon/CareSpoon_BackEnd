package com.carespoon.user.dto;

import com.carespoon.user.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
public class UserSaveRequestDto {
    private String email;

    private String name;

    private String uuid;

    private String role;

    @Builder
    public UserSaveRequestDto(String email, String name, String role){
        this.email = email;
        this.name = name;
        this.role = role;
        this.uuid = UUID.randomUUID().toString();
    }
    public User toEntity() {
        return User.builder()
                .email(email)
                .role(role)
                .name(name)
                .uuid(uuid)
                .build();
    }
}
