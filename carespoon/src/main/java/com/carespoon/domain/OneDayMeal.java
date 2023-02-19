package com.carespoon.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@Entity
public class OneDayMeal {

    @Id
    @CreatedDate
    private LocalDate mealDate;

    @Column
    private int meal_Kcal;

    @Column
    private int meal_Carbon;

    @Column
    private int meal_Fat;

    @Column
    private int meal_Protein;
}
