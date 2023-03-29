package com.carespoon.controller;

import com.carespoon.Repository.MenuRepository;
import com.carespoon.Repository.OneMealRepositoryCustom;
import com.carespoon.domain.Menu;
import com.carespoon.domain.User;
import com.carespoon.dto.OneMealResponseDto;
import com.carespoon.dto.OneMealSaveRequestDto;
import com.carespoon.service.MenuService;
import com.carespoon.service.OneMealService;
import com.carespoon.service.UserService;
import com.querydsl.core.Tuple;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
public class OneMealController {
    private final OneMealService oneMealService;

    @Autowired
    @Qualifier("oneMealRepositoryImpl")
    private OneMealRepositoryCustom oneMealRepositoryCustom;

    private UserService userService;

    private MenuService menuService;

    private MenuRepository menuRepository;

    @GetMapping("/dailynurition/{userId}")
    public List<Tuple> getDaily(@PathVariable UUID userId, @RequestParam String date) {
        LocalDate localDate = LocalDate.parse(date);
        User user = userService.findByUuid(userId);
        List<Tuple> dailyNutrition = oneMealRepositoryCustom.findOneMealByCreatedTime(user, localDate);
        return dailyNutrition;
    }

    @GetMapping("/monthlynutrition/{userId}")
    public List<Tuple> getMonthly(@PathVariable UUID userId, @RequestParam String month) {
        YearMonth yearMonth = YearMonth.parse(month);
        User user = userService.findByUuid(userId);
        List<Tuple> monthlyNutrition = oneMealRepositoryCustom.findOneMealByCreatedMonth(user, yearMonth);
        return monthlyNutrition;
    }

    @PostMapping("/onemeal")
//    public Long save(@RequestBody OneMealSaveRequestDto requestDto) {
//        return oneMealService.save(requestDto);
//    }
    public Long save(@RequestBody List<String> menus, @PathVariable UUID userid) throws IOException {
        User user = userService.findByUuid(userid);
        List<Menu> menuList = me
        double kcal = 0, protein = 0, carbon = 0, fat = 0;
        for (int i = 0; i < menus.size(); i++) {
            kcal += menuService.findByMenuName(menus.get(i)).getMenu_Kcal();
            protein += menuService.findByMenuName(menus.get(i)).getMenu_Protein();
            fat += menuService.findByMenuName(menus.get(i)).getMenu_Fat();
            carbon += menuService.findByMenuName(menus.get(i)).getMenu_Carbon();
        }
        OneMealSaveRequestDto requestDto = OneMealSaveRequestDto.builder()
                .meal_Kcal(kcal).meal_Protein(protein).meal_Fat(fat).meal_Carbon(carbon).build();
        return oneMealService.save(requestDto);
    }


    @GetMapping("/onemeal/{userId}")
    public List<OneMealResponseDto> findById(@PathVariable UUID userId) {
        return oneMealService.findByUser(userId);
    }


}
