package com.carespoon.userInfo.domain;

import javax.persistence.*;

import com.carespoon.user.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Entity
@NoArgsConstructor
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    @OneToOne
    @JoinColumn(name = "userId")
    private User user;

    @Builder
    public UserInfo(User user, int age, int sex, double height, double weight){
        this.user = user;
        this.age = age;
        this.sex = sex;
        this.height = height;
        this.weight = weight;
        if(sex == 0){
            this.metabolicRate = 88.4 + (13.4*weight) + 4.8* height - 5.68 * age;
                    //66 + (13.8 x 몸무게(kg)) + (5 x 키(cm)) - (6.8 x 나이)
        }else{
            this.metabolicRate = 447.6 + (9.25*weight)+ 3.1*height - 4.33*age;
                    //BMR = 655 + (9.6 x 몸무게(kg)) + (1.8 x 키(cm)) - (4.7 x 나이)
        }
    }

    public void update(User user, int age, int sex, double height, double weight){
        this.user = user;
        this.age = age;
        this.sex = sex;
        this.height = height;
        this.weight = weight;
        if(sex == 0){
            this.metabolicRate = 88.4 + (13.4*weight) + 4.8* height - 5.68 * age;
        }else{
            this.metabolicRate = 447.6 + (9.25*weight)+ 3.1*height - 4.33*age;
        }
    }
}
