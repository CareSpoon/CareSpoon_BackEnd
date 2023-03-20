package com.carespoon.domain;

import javax.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

@Getter
@Entity
@NoArgsConstructor
public class OneMeal{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column
    private int meal_Kcal;

    @Column
    private int meal_Carbon;

    @Column
    private int meal_Fat;

    @Column
    private int meal_Protein;

    @OneToOne(mappedBy = "oneMeal", cascade = CascadeType.ALL)
    private Image mealImage;

    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date eatDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Builder
    public OneMeal(int meal_Kcal, int meal_Carbon, int meal_Fat, int meal_Protein, Date eatDate){
        this.meal_Kcal = meal_Kcal;
        this.meal_Fat = meal_Fat;
        this.meal_Carbon = meal_Carbon;
        this.meal_Protein = meal_Protein;
        this.eatDate = eatDate;
    }



}
