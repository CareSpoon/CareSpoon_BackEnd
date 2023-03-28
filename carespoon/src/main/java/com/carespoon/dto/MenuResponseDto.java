package com.carespoon.dto;

import lombok.Getter;


@Getter
public class MenuResponseDto {

    public Long id;

    public String menuName;

    private int menu_Kcal;

    private int menu_Carbon;

    private int menu_Fat;

    private int menu_Protein;

    public MenuResponseDto(Long id, String menuName, int menu_Kcal, int menu_Protein, int menu_Carbon, int menu_Fat){
        this.id = id;
        this.menuName = menuName;
        this.menu_Carbon = menu_Carbon;
        this.menu_Kcal = menu_Kcal;
        this.menu_Protein = menu_Protein;
        this.menu_Fat = menu_Fat;
    }
}
