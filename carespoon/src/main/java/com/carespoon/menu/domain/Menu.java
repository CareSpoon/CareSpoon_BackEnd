package com.carespoon.menu.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column
    public String menuName;

    @Column
    private double menu_kcal;

    @Column
    private double menu_carbon;

    @Column
    private double menu_fat;

    @Column
    private double menu_protein;


    @Column
    private double menu_na;

    @Column
    private double menu_cal;

    @Column
    private double menu_fe;
    public Menu(Long id, String menuName, double menu_Kcal, double menu_Carbon, double menu_Fat, double menu_Protein, double menu_na, double menu_cal, double menu_fe){
        this.id = id;
        this.menuName = menuName;
        this.menu_kcal = menu_Kcal;
        this.menu_carbon = menu_Carbon;
        this.menu_fat = menu_Fat;
        this.menu_protein = menu_Protein;
        this.menu_na = menu_na;
        this.menu_cal = menu_cal;
        this.menu_fe = menu_fe;
    }
}
