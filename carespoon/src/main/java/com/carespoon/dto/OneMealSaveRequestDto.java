package com.carespoon.dto;

import com.carespoon.domain.OneMeal;
import com.carespoon.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@NoArgsConstructor
public class OneMealSaveRequestDto {
    private int meal_Kcal;
    private int meal_Carbon;
    private int meal_Fat;
    private int meal_Protein;

    private Date eatDate;

    private User user;
    private ImageDto imageDto;
    @Builder
    public OneMealSaveRequestDto(int meal_Kcal, int meal_Carbon, int meal_Fat, int meal_Protein,  Date eatDate, User user){
        this.meal_Kcal = meal_Kcal;
        this.meal_Fat = meal_Fat;
        this.meal_Carbon = meal_Carbon;
        this.meal_Protein = meal_Protein;
        this.eatDate = eatDate;
        this.user = user;
    }



    public OneMeal toEntity(){
        return OneMeal.builder()
                .meal_Protein(meal_Protein)
                .meal_Carbon(meal_Carbon)
                .meal_Kcal(meal_Kcal)
                .meal_Fat(meal_Fat)
                .eatDate(eatDate)
                .user(user)
                .build();
    }
}
