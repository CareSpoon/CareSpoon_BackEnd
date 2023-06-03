package com.carespoon.oneMeal.dto;

import com.carespoon.menu.domain.Menu;
import com.carespoon.oneMeal.domain.OneMeal;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;


@Getter
public class OneMealResponseDto {
    private double meal_Kcal;
    private double meal_Carbon;
    private double meal_Fat;
    private double meal_Protein;
    private double meal_na;
    private double meal_cal;
    private double meal_fe;
    private List<String> menus = new ArrayList<>();
    private String eatDate;
    private String eatMonth;
    private String eatTime;
    private String tag;
    private String imageUrl;

    public OneMealResponseDto(OneMeal entity){
        this.meal_Kcal = entity.getMeal_Kcal();
        this.meal_Fat = entity.getMeal_Fat();
        this.meal_Carbon = entity.getMeal_Carbon();
        this.meal_Protein = entity.getMeal_Protein();
        this.meal_na = entity.getMeal_na();
        this.meal_cal = entity.getMeal_cal();
        this.meal_fe = entity.getMeal_fe();
        List<Menu> menuFromMeal = entity.getMenus();
        for(Menu menu : menuFromMeal){
            menus.add(menu.getMenuName());
        }
        this.eatTime = entity.getEatTime();
        this.eatDate = entity.getEatDate();
        this.eatMonth =entity.getEatMonth();
        this.tag = entity.getTag();
        this.imageUrl = entity.getImageUrl();
    }
}
