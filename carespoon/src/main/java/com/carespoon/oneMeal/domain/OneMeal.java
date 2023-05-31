package com.carespoon.oneMeal.domain;

import javax.persistence.*;

import com.carespoon.menu.domain.Menu;
import com.carespoon.user.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.*;

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
    private double meal_na;

    @Column
    private double meal_cal;

    @Column
    private double meal_fe;

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

    @OneToMany
    @JoinColumn(name = "menuId")
    private List<Menu> menus;

    @Builder
    public OneMeal(double meal_Kcal, double meal_Carbon, double meal_Fat, double meal_Protein, double meal_na, double meal_cal, double meal_fe, String eatDate, String eatMonth, String eatTime,String tag, User user, String imageUrl, List<Menu> menus) {
        this.meal_Kcal = meal_Kcal;
        this.meal_Fat = meal_Fat;
        this.meal_Carbon = meal_Carbon;
        this.meal_Protein = meal_Protein;
        this.meal_na = meal_na;
        this.meal_cal = meal_cal;
        this.meal_fe = meal_fe;
        this.eatDate = eatDate;
        this.eatMonth = eatMonth;
        this.eatTime = eatTime;
        this.tag = tag;
        this.user = user;
        this.imageUrl = imageUrl;
        this.menus = menus;
    }

}
