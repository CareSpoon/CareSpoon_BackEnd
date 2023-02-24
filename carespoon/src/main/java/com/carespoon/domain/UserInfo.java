package com.carespoon.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
public class UserInfo {
    @Id
    private int userId;

    @Column
    private int age;

    @Column
    private int sex;
    @Column
    private double height;

    @Column
    private double weight;

    @Column
    private double metabolicRate;

    @Builder
    public UserInfo(int userId, int age, LocalDate now, int sex, double height, double weight){
        this.userId = userId;
        this.age = age;
        this.sex = sex;
        this.height = height;
        this.weight = weight;
        if(sex == 0){
            this.metabolicRate = 66 + (13.8*weight) + 5 * height - 6.8 * age;
                    //66 + (13.8 x 몸무게(kg)) + (5 x 키(cm)) - (6.8 x 나이)
        }
    }
}
