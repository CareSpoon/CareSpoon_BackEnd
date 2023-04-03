package com.carespoon.user.domain;


import javax.persistence.*;

import com.carespoon.UserInfo.domain.UserInfo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;



@Getter
@NoArgsConstructor
@Entity
public class User {

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String name;

    @Id
    @Column(nullable = false)
    private UUID uuid;

    @Column(nullable = false)
    private int role;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private UserInfo userInfo;

    @Builder
    public User(String email, String name, int role){
        this.email = email;
        this.name = name;
        this.role = role;
        this.uuid = UUID.randomUUID();
    }

}
