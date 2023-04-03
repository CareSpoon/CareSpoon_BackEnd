package com.carespoon.UserInfo.dto;

import com.carespoon.user.domain.User;
import com.carespoon.UserInfo.domain.UserInfo;
import lombok.Getter;

@Getter
public class UserInfoResponseDto {
    private User user;

    private double height;

    private int age;

    private double weight;

    private double metabolicRate;

    private int sex;

    public UserInfoResponseDto(UserInfo Entity){
        this.user = Entity.getUser();
        this.age = Entity.getAge();
        this.height = Entity.getHeight();
        this.sex = Entity.getSex();
        this.weight = Entity.getWeight();
        this.metabolicRate = Entity.getMetabolicRate();
    }
}
