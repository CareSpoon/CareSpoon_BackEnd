package com.carespoon.oneMeal.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
public class MonthlyMealResponseDto {
    private double meal_Kcal;
    private double meal_Carbon;
    private double meal_Fat;
    private double meal_Protein;
    private String eatMonth;

    @QueryProjection
    public MonthlyMealResponseDto(double meal_Kcal, double meal_Carbon, double meal_Fat, double meal_Protein, String eatMonth ){
        this.meal_Kcal = meal_Kcal;
        this.meal_Carbon = meal_Carbon;
        this.meal_Fat = meal_Fat;
        this.meal_Protein = meal_Protein;
        this.eatMonth = eatMonth;
    }
}
