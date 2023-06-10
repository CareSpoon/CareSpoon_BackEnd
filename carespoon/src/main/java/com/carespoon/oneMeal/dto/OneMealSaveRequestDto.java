package com.carespoon.oneMeal.dto;

import com.carespoon.menu.domain.Menu;
import com.carespoon.oneMeal.domain.OneMeal;
import com.carespoon.user.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
public class OneMealSaveRequestDto {
    private double meal_Kcal;
    private double meal_Carbon;
    private double meal_Fat;
    private double meal_Protein;
    private double meal_na;
    private double meal_cal;
    private double meal_fe;
    private String eatDate;
    private String eatMonth;
    private String eatTime;
    private User user;

    private String imageUrl;

    private String tag;
    @Builder
    public OneMealSaveRequestDto(double meal_Kcal, double meal_Carbon, double meal_Fat, double meal_Protein, double meal_na, double meal_cal, double meal_fe,  String imageUrl,String eatDate,String eatMonth, String eatTime,String tag, User user){
        this.meal_Kcal = meal_Kcal;
        this.meal_Fat = meal_Fat;
        this.meal_Carbon = meal_Carbon;
        this.meal_Protein = meal_Protein;
        this.meal_na = meal_na;
        this.meal_cal = meal_cal;
        this.meal_fe = meal_fe;
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
                .meal_na(meal_na)
                .meal_cal(meal_cal)
                .meal_fe(meal_fe)
                .imageUrl(imageUrl)
                .eatDate(eatDate)
                .eatMonth(eatMonth)
                .eatTime(eatTime)
                .tag(tag)
                .user(user)
                .build();
    }
}
