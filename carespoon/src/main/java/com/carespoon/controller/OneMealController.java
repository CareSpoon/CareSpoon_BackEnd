package com.carespoon.controller;

import com.carespoon.Repository.MenuRepository;
import com.carespoon.Repository.OneMealRepositoryCustom;
import com.carespoon.domain.Menu;
import com.carespoon.domain.OneMeal;
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

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
public class OneMealController {
    private OneMealService oneMealService;

    @Autowired
    @Qualifier("oneMealRepositoryImpl")
    private OneMealRepositoryCustom oneMealRepositoryCustom;

    private UserService userService;

    @PostMapping("/onemeal")
    public ResponseEntity<OneMealResponseDto> addOneMeal(@RequestParam UUID userId,@RequestParam List<String> menuNames, @RequestParam MultipartFile image) throws IOException{
        OneMealResponseDto oneMeal = new OneMealResponseDto(oneMealService.save(userId, menuNames, image));
        return ResponseEntity.ok(oneMeal);
    }

    @GetMapping("/dailynurition/{userId}")
    public List<Tuple> getDaily(@PathVariable UUID userId, @RequestParam String date) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date listDate = dateFormat.parse(date);
        User user = userService.findByUuid(userId);
        List<Tuple> dailyNutrition = oneMealRepositoryCustom.findOneMealByCreatedTime(user, listDate);
        return dailyNutrition;
    }

    @GetMapping("/monthlynutrition/{userId}")
    public List<Tuple> getMonthly(@PathVariable UUID userId, @RequestParam String month) {
        YearMonth yearMonth = YearMonth.parse(month);
        User user = userService.findByUuid(userId);
        List<Tuple> monthlyNutrition = oneMealRepositoryCustom.findOneMealByCreatedMonth(user, yearMonth);
        return monthlyNutrition;
    }

    @GetMapping("/onemeal/{userId}")
    public List<OneMealResponseDto> findById(@PathVariable UUID userId) {
        return oneMealService.findByUser(userId);
    }


}
