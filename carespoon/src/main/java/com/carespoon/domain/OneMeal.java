package com.carespoon.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.annotation.CreatedDate;

import java.net.URL;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
public class OneMeal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @CreatedDate
    private LocalDateTime mealTime;

    @Column
    private int meal_Kcal;

    @Column
    private int meal_Carbon;

    @Column
    private int meal_Fat;

    @Column
    private int meal_Protein;

    @Column(nullable = false)
    private Long imageId;

    @Builder
    public OneMeal(LocalDateTime mealTime, int meal_Kcal, int meal_Carbon, int meal_Fat, int meal_Protein, Long imageId){
        this.mealTime = mealTime;
        this.meal_Kcal = meal_Kcal;
        this.meal_Fat = meal_Fat;
        this.meal_Carbon = meal_Carbon;
        this.meal_Protein = meal_Protein;
        this.imageId = imageId;
    }



}
