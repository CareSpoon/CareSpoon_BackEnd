package com.carespoon.dto;

import com.carespoon.domain.OneMeal;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.net.URL;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class OneMealSaveRequestDto {
    private LocalDateTime mealTime;
    private int meal_Kcal;
    private int meal_Carbon;
    private int meal_Fat;
    private int meal_Protein;

    private Long imageId;

    @Builder
    public OneMealSaveRequestDto(LocalDateTime mealTime, int meal_Kcal, int meal_Carbon, int meal_Fat, int meal_Protein , Long imageId){
        this.mealTime = mealTime;
        this.meal_Kcal = meal_Kcal;
        this.meal_Fat = meal_Fat;
        this.meal_Carbon = meal_Carbon;
        this.meal_Protein = meal_Protein;
        this.imageId = imageId;
    }



    public OneMeal toEntity(){
        return OneMeal.builder()
                .mealTime(mealTime)
                .meal_Protein(meal_Protein)
                .meal_Carbon(meal_Carbon)
                .meal_Kcal(meal_Kcal)
                .meal_Fat(meal_Fat)
                .imageId(imageId)
                .build();
    }
}
