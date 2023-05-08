package com.carespoon.oneMeal.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
public class MealResponseDto {
    private double meal_Kcal;
    private double meal_Carbon;
    private double meal_Fat;
    private double meal_Protein;
    private String eatDate;
    private String imageUrl;
    @QueryProjection
    public MealResponseDto(double meal_Kcal, double meal_Carbon, double meal_Fat, double meal_Protein, String imageUrl, String eatDate ){
        this.meal_Kcal = meal_Kcal;
        this.meal_Carbon = meal_Carbon;
        this.meal_Fat = meal_Fat;
        this.meal_Protein = meal_Protein;
        this.imageUrl = imageUrl;
        this.eatDate = eatDate;
    }
}
