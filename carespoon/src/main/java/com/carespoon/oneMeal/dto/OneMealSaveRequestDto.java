package com.carespoon.oneMeal.dto;

import com.carespoon.oneMeal.domain.OneMeal;
import com.carespoon.user.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class OneMealSaveRequestDto {
    private double meal_Kcal;
    private double meal_Carbon;
    private double meal_Fat;
    private double meal_Protein;

    private Date eatDate;

    private User user;

    private String imageUrl;

    @Builder
    public OneMealSaveRequestDto(double meal_Kcal, double meal_Carbon, double meal_Fat, double meal_Protein, String imageUrl, Date eatDate, User user){
        this.meal_Kcal = meal_Kcal;
        this.meal_Fat = meal_Fat;
        this.meal_Carbon = meal_Carbon;
        this.meal_Protein = meal_Protein;
        this.imageUrl = "https://storage.googleapis.com/carespoon-storage/"+imageUrl;
        this.eatDate = eatDate;
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
                .user(user)
                .build();
    }
}
