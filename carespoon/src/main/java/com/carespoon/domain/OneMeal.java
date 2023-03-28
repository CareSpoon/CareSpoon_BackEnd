package com.carespoon.domain;

import javax.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;
import java.util.UUID;

@Getter
@Entity
@NoArgsConstructor
public class OneMeal{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column
    private double meal_Kcal;

    @Column
    private double meal_Carbon;

    @Column
    private double meal_Fat;

    @Column
    private double meal_Protein;


    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date eatDate;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    private String imageUrl;
    @Builder
    public OneMeal(double meal_Kcal, double meal_Carbon, double meal_Fat, double meal_Protein, Date eatDate, User user, String imageUrl) {
        this.meal_Kcal = meal_Kcal;
        this.meal_Fat = meal_Fat;
        this.meal_Carbon = meal_Carbon;
        this.meal_Protein = meal_Protein;
        this.eatDate = eatDate;
        this.user = user;
        this.imageUrl = imageUrl;
    }

}
