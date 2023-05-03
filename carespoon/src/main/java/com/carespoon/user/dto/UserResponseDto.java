package com.carespoon.user.dto;

import com.carespoon.user.domain.User;
import com.carespoon.userInfo.domain.UserInfo;

import java.util.UUID;

public class UserResponseDto {

    private String email;

    private String name;

    private String uuid;

    private String role;

    private UserInfo userInfo;

    public UserResponseDto(User entity) {
        this.email = entity.getEmail();
        this.name = entity.getName();
        this.role = entity.getRole();
        this.uuid = entity.getUuid();
    }

}
