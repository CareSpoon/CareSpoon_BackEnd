package com.carespoon.domain;

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
    private int menu_Kcal;

    @Column
    private int menu_Carbon;

    @Column
    private int menu_Fat;

    @Column
    private int menu_Protein;

    public Menu(Long id, String menuName, int menu_Kcal, int menu_Carbon, int menu_Fat, int menu_Protein){
        this.id = id;
        this.menuName = menuName;
        this.menu_Kcal = menu_Kcal;
        this.menu_Carbon = menu_Carbon;
        this.menu_Fat = menu_Fat;
        this.menu_Protein = menu_Protein;
    }
}
