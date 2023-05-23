package com.carespoon.oneMeal.controller;

import com.carespoon.common.dto.ApiResponseDto;
import com.carespoon.exception.Success;
import com.carespoon.exception.model.NotMealException;
import com.carespoon.exception.model.NotMenuException;
import com.carespoon.oneMeal.dto.*;
import com.carespoon.oneMeal.repository.OneMealRepositoryCustom;
import com.carespoon.user.domain.User;
import com.carespoon.oneMeal.service.OneMealService;
import com.carespoon.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class OneMealController {
    private final OneMealService oneMealService;

    private final UserService userService;

//    @PostMapping("/onemeal")
//    public ResponseEntity<OneMealResponseDto> addOneMeal(@RequestParam String userId, @RequestParam List<String> menuNames, @RequestBody MultipartFile image) throws IOException, ParseException {
//        OneMealResponseDto oneMeal = new OneMealResponseDto(oneMealService.save(userId, menuNames, image));
//        return ResponseEntity.ok(oneMeal);
//    }

    @PostMapping("/onemeal")
    public ApiResponseDto<OneMealResponseDto> save(@RequestParam String userId, @RequestParam MultipartFile image, @RequestParam String tag) throws IOException, NotMenuException {
        OneMealResponseDto oneMeal = new OneMealResponseDto(oneMealService.saveTest(userId,image, tag));
        return ApiResponseDto.success(Success.MENU_FIND_SUCCESS, oneMeal);
    }

    @GetMapping("/dailystatistics/{userId}/{date}")
    public DailyMealResponseDto getDailyStatistics(@PathVariable String userId, @PathVariable String date) throws NotMealException {
        User user = userService.findByUuid(userId);
        DailyMealResponseDto dailyNutrition = oneMealService.findOneMealByCreatedDate(user, date).get(0);
        return dailyNutrition;
    }

    @GetMapping("/monthlystatistics/{userId}/{month}")
    public MonthlyMealResponseDto getMonthlyStatistics(@PathVariable String userId, @PathVariable String month) throws NotMealException {
        User user = userService.findByUuid(userId);
        MonthlyMealResponseDto monthlyNutrition = oneMealService.findOneMealByCreatedMonth(user, month).get(0);
        return monthlyNutrition;
    }

    @GetMapping("/dailymeals/{userId}/{date}")
    public List<MealResponseDto> getDaymeals(@PathVariable String userId, @PathVariable String date) throws NotMealException{
        User user = userService.findByUuid(userId);
        List<MealResponseDto> results = oneMealService.findMealByCreatedDate(user, date);
        return results;
    }

    @GetMapping("/onemeal/{userId}")
    public List<OneMealResponseDto> findById(@PathVariable String userId) {
        return oneMealService.findByUser(userId);
    }


}
