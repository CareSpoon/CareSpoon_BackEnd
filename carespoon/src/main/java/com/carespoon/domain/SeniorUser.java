package com.carespoon.domain;


import javax.persistence.*;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Random;
import java.util.UUID;


@Getter
@NoArgsConstructor
@Entity
public class SeniorUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String name;

    private String uniqueCode;

    @Builder
    public SeniorUser(String email, String name){
        this.email = email;
        this.name = name;
        Random random = new Random();
        this.uniqueCode = String.format("%06d", random.nextInt(1000000));
    }

}
