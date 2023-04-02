package com.carespoon.dto;

import com.carespoon.domain.User;
import com.carespoon.domain.UserInfo;

import java.util.UUID;

import static com.carespoon.domain.User.*;

public class UserResponseDto {

    private String email;

    private String name;

    private UUID uuid;

    private int role;

    private UserInfo userInfo;

    public UserResponseDto(User entity) {
        this.email = entity.getEmail();
        this.name = entity.getName();
        this.role = entity.getRole();
        this.uuid = entity.getUuid();
    }

}
