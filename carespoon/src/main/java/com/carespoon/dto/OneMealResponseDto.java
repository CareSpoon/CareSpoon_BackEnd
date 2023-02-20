package com.carespoon.dto;

import com.carespoon.domain.OneMeal;
import lombok.Getter;

import java.net.URL;
import java.time.LocalDateTime;

@Getter
public class OneMealResponseDto {

    private Long id;
    private LocalDateTime mealTime;
    private int meal_Kcal;
    private int meal_Carbon;
    private int meal_Fat;
    private int meal_Protein;

    public OneMealResponseDto(OneMeal entity){
        this.id = entity.getId();
        this.mealTime = entity.getMealTime();
        this.meal_Kcal = entity.getMeal_Kcal();
        this.meal_Fat = entity.getMeal_Fat();
        this.meal_Carbon = entity.getMeal_Carbon();
        this.meal_Protein = entity.getMeal_Protein();
    }
}
