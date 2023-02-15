package com.carespoon.dto;

import com.carespoon.domain.OneMeal;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class OneMealSaveRequestDto {
    private LocalDateTime mealTime;
    private int meal_Kcal;
    private int meal_Carbon;
    private int meal_Fat;
    private int meal_Protein;

    @Builder
    public OneMealSaveRequestDto(LocalDateTime mealTime, int meal_Kcal, int meal_Carbon, int meal_Fat, int meal_Protein){
        this.mealTime = mealTime;
        this.meal_Kcal = meal_Kcal;
        this.meal_Fat = meal_Fat;
        this.meal_Carbon = meal_Carbon;
        this.meal_Protein = meal_Protein;
    }

    public OneMeal toEntity(){
        return OneMeal.builder()
                .mealTime(mealTime)
                .meal_Protein(meal_Protein)
                .meal_Carbon(meal_Carbon)
                .meal_Kcal(meal_Kcal)
                .meal_Fat(meal_Fat)
                .build();
    }
}
