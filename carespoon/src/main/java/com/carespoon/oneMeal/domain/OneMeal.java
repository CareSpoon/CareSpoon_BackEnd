package com.carespoon.oneMeal.domain;

import javax.persistence.*;

import com.carespoon.user.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class OneMeal{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column
    private double meal_Kcal;

    @Column
    private double meal_Carbon;

    @Column
    private double meal_Fat;

    @Column
    private double meal_Protein;


    @Column
    private String eatDate;

    @Column
    private String eatMonth;

    @Column
    private String eatTime;

    @Column
    private String tag;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    private String imageUrl;
    @Builder
    public OneMeal(double meal_Kcal, double meal_Carbon, double meal_Fat, double meal_Protein, String eatDate, String eatMonth, String eatTime,String tag, User user, String imageUrl) {
        this.meal_Kcal = meal_Kcal;
        this.meal_Fat = meal_Fat;
        this.meal_Carbon = meal_Carbon;
        this.meal_Protein = meal_Protein;
        this.eatDate = eatDate;
        this.eatMonth = eatMonth;
        this.eatTime = eatTime;
        this.tag = tag;
        this.user = user;
        this.imageUrl = imageUrl;
    }

}
