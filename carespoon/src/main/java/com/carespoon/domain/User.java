package com.carespoon.domain;


import javax.persistence.*;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Getter
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String name;

    private UUID uuid;

    private int role;

    @Builder
    public User(String email, String name, int role){
        this.email = email;
        this.name = name;
        this.role = role;
    }

}
