package com.carespoon.oneMeal.dto;

import com.carespoon.oneMeal.domain.OneMeal;
import com.carespoon.user.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class OneMealSaveRequestDto {
    private double meal_Kcal;
    private double meal_Carbon;
    private double meal_Fat;
    private double meal_Protein;

    private String eatDate;
    private String eatMonth;
    private String eatTime;
    private User user;

    private String imageUrl;

    private String tag;
    @Builder
    public OneMealSaveRequestDto(double meal_Kcal, double meal_Carbon, double meal_Fat, double meal_Protein, String imageUrl,String eatDate,String eatMonth, String eatTime,String tag, User user){
        this.meal_Kcal = meal_Kcal;
        this.meal_Fat = meal_Fat;
        this.meal_Carbon = meal_Carbon;
        this.meal_Protein = meal_Protein;
        this.imageUrl = "https://storage.googleapis.com/carespoon-storage/"+imageUrl;
        this.eatDate = eatDate;
        this.eatMonth = eatMonth;
        this.eatTime = eatTime;
        this.tag = tag;
        this.user = user;
    }


    public OneMeal toEntity(){
        return OneMeal.builder()
                .meal_Protein(meal_Protein)
                .meal_Carbon(meal_Carbon)
                .meal_Kcal(meal_Kcal)
                .meal_Fat(meal_Fat)
                .imageUrl(imageUrl)
                .eatDate(eatDate)
                .eatMonth(eatMonth)
                .eatTime(eatTime)
                .tag(tag)
                .user(user)
                .build();
    }
}
