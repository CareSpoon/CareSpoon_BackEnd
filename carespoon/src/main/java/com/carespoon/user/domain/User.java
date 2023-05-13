package com.carespoon.user.domain;


import javax.persistence.*;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;



@Getter
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String uuid;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String name;


    @Column(nullable = false)
    private String role;

    @Builder
    public User(String email, String name, String role, String uuid){
        this.email = email;
        this.name = name;
        this.role = role;
        this.uuid = uuid;
    }

}
