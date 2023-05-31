package com.carespoon.oneMeal.dto;

import com.carespoon.menu.domain.Menu;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import java.util.*;

@Getter
public class MealResponseDto {
    private double meal_Kcal;
    private double meal_Carbon;
    private double meal_Fat;
    private double meal_Protein;
    private double meal_na;
    private double meal_cal;
    private double meal_fe;
    private List<String> menus;
    private String eatDate;
    private String tag;
    private String eatTime;
    private String imageUrl;
    @QueryProjection
    public MealResponseDto(double meal_Kcal, double meal_Carbon, double meal_Fat, double meal_Protein, double meal_na, double meal_cal, double meal_fe, List<Menu> menuNames, String imageUrl, String eatDate, String eatTime, String tag ){
        this.meal_Kcal = meal_Kcal;
        this.meal_Carbon = meal_Carbon;
        this.meal_Fat = meal_Fat;
        this.meal_Protein = meal_Protein;
        this.meal_na = meal_na;
        this.meal_cal = meal_cal;
        this.meal_fe = meal_fe;
        this.menus = new ArrayList<>();
        for(Menu menu : menuNames){
            menus.add(menu.getMenuName());
        }
        this.imageUrl = imageUrl;
        this.eatDate = eatDate;
        this.eatTime = eatTime;
        this.tag = tag;
    }
}
