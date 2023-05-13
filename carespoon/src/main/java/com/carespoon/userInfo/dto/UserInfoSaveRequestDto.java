package com.carespoon.userInfo.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Getter
@NoArgsConstructor
public class UserInfoSaveRequestDto {

    private String userId;

    private double height;

    private int age;

    private double weight;

    private double metabolicRate;

    private int sex;

    @Builder
    public UserInfoSaveRequestDto(String userId, String birth, int sex, double height, double weight){
        LocalDate now = LocalDate.now();
        this.userId = userId;
        this.age = now.getYear() - Integer.valueOf(birth);
        this.sex = sex;
        this.height = height;
        this.weight = weight;
        if(sex == 0){
            //man
            this.metabolicRate = 88.4 + (13.4*weight) + 4.8* height - 5.68 * age;
        }else{
            //woman
            this.metabolicRate = 447.6 + (9.25*weight)+ 3.1*height - 4.33*age;
        }
    }


}
