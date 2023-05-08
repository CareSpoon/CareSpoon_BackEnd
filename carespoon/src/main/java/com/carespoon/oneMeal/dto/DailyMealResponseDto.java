package com.carespoon.oneMeal.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
public class DailyMealResponseDto {
    private double meal_Kcal;
    private double meal_Carbon;
    private double meal_Fat;
    private double meal_Protein;
    private String eatDate;

    @QueryProjection
    public DailyMealResponseDto(double meal_Kcal, double meal_Carbon, double meal_Fat, double meal_Protein, String eatDate ){
        this.meal_Kcal = meal_Kcal;
        this.meal_Carbon = meal_Carbon;
        this.meal_Fat = meal_Fat;
        this.meal_Protein = meal_Protein;
        this.eatDate = eatDate;
    }
}
