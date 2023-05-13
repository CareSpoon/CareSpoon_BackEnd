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
    private double menu_Kcal;

    @Column
    private double menu_Carbon;

    @Column
    private double menu_Fat;

    @Column
    private double menu_Protein;

    public Menu(Long id, String menuName, double menu_Kcal, double menu_Carbon, double menu_Fat, double menu_Protein){
        this.id = id;
        this.menuName = menuName;
        this.menu_Kcal = menu_Kcal;
        this.menu_Carbon = menu_Carbon;
        this.menu_Fat = menu_Fat;
        this.menu_Protein = menu_Protein;
    }
}
