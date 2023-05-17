package com.carespoon.oneMeal.dto;

import com.carespoon.oneMeal.domain.OneMeal;
import lombok.Getter;


@Getter
public class OneMealResponseDto {

    private Long id;
    private double meal_Kcal;
    private double meal_Carbon;
    private double meal_Fat;
    private double meal_Protein;

    private String eatDate;
    private String eatMonth;
    private String eatTime;
    private String tag;
    private String imageUrl;

    public OneMealResponseDto(OneMeal entity){
        this.id = entity.getId();
        this.meal_Kcal = entity.getMeal_Kcal();
        this.meal_Fat = entity.getMeal_Fat();
        this.meal_Carbon = entity.getMeal_Carbon();
        this.meal_Protein = entity.getMeal_Protein();
        this.eatTime = entity.getEatTime();
        this.eatDate = entity.getEatDate();
        this.eatMonth =entity.getEatMonth();
        this.tag = entity.getTag();
        this.imageUrl = entity.getImageUrl();
    }

}
