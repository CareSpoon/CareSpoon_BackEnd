package com.carespoon.dto;

import com.carespoon.domain.User;
import com.carespoon.domain.UserInfo;

import javax.persistence.*;
import java.util.UUID;

public class UserResponseDto{
    private Long id;

    private String email;

    private String name;

    private UUID uuid;

    private int role;

    private UserInfo userInfo;

    public UserResponseDto(User entity){
        this.email = entity.getEmail();
        this.name = entity.getName();
        this.role = entity.getRole();
    }

}
