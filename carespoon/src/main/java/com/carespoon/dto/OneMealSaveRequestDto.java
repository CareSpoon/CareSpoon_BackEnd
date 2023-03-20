package com.carespoon.dto;

import com.carespoon.domain.OneMeal;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class OneMealSaveRequestDto {
    private int meal_Kcal;
    private int meal_Carbon;
    private int meal_Fat;
    private int meal_Protein;

    private LocalDate eatDate;

    private LocalDateTime createdDate;

    private ImageDto imageDto;
    @Builder
    public OneMealSaveRequestDto(int meal_Kcal, int meal_Carbon, int meal_Fat, int meal_Protein,  LocalDate eatDate, LocalDateTime createdDate){
        this.meal_Kcal = meal_Kcal;
        this.meal_Fat = meal_Fat;
        this.meal_Carbon = meal_Carbon;
        this.meal_Protein = meal_Protein;
        this.eatDate = eatDate;
        this.createdDate = createdDate;

    }



    public OneMeal toEntity(){
        return OneMeal.builder()
                .meal_Protein(meal_Protein)
                .meal_Carbon(meal_Carbon)
                .meal_Kcal(meal_Kcal)
                .meal_Fat(meal_Fat)
                .build();
    }
}
