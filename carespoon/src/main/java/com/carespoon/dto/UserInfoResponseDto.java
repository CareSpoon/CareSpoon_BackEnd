package com.carespoon.dto;

import com.carespoon.domain.UserInfo;
import lombok.Getter;

@Getter
public class UserInfoResponseDto {
    private int userId;

    private double height;

    private int age;

    private double weight;

    private double metabolicRate;

    private int sex;

    public UserInfoResponseDto(UserInfo Entity){
        this.userId = Entity.getUserId();
        this.age = Entity.getAge();
        this.height = Entity.getHeight();
        this.sex = Entity.getSex();
        this.weight = Entity.getWeight();
        this.metabolicRate = Entity.getMetabolicRate();
    }
}
