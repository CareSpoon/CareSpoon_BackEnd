package com.carespoon.oneMeal.controller;

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

    @Autowired
    @Qualifier("oneMealRepositoryImpl")
    private OneMealRepositoryCustom oneMealRepositoryCustom;

    private final UserService userService;

    @PostMapping("/onemeal")
    public ResponseEntity<OneMealResponseDto> addOneMeal(@RequestParam String userId, @RequestParam List<String> menuNames, @RequestBody MultipartFile image) throws IOException, ParseException {
        OneMealResponseDto oneMeal = new OneMealResponseDto(oneMealService.save(userId, menuNames, image));
        return ResponseEntity.ok(oneMeal);
    }

    @PostMapping("/onemealTest")
    public ResponseEntity<OneMealResponseDto> saveTest(@RequestParam String userId, @RequestParam MultipartFile image) throws IOException{
        OneMealResponseDto oneMeal = new OneMealResponseDto(oneMealService.saveTest(userId,image));
        return ResponseEntity.ok(oneMeal);
    }
    //사진 업로드 테스트 코드
//    private final GcsService gcsService;
//    @PostMapping("/imagetest")
//    public String addTest(@RequestBody MultipartFile file) throws IOException{
//        return gcsService.uploadImage(file);
//    }
    @GetMapping("/dailystatistics/{userId}/{date}")
    public DailyMealResponseDto getDailyStatistics(@PathVariable String userId, @PathVariable String date) {
        User user = userService.findByUuid(userId);
        DailyMealResponseDto dailyNutrition = oneMealRepositoryCustom.findOneMealByCreatedTime(user, date).get(0);
        return dailyNutrition;
    }

    @GetMapping("/monthlystatistics/{userId}/{month}")
    public MonthlyMealResponseDto getMonthlyStatistics(@PathVariable String userId, @PathVariable String month) {
        User user = userService.findByUuid(userId);
        MonthlyMealResponseDto monthlyNutrition = oneMealRepositoryCustom.findOneMealByCreatedMonth(user, month).get(0);
        return monthlyNutrition;
    }

    @GetMapping("/dailymeals/{userId}/{date}")
    public List<MealResponseDto> getDaymeals(@PathVariable String userId, @PathVariable String date) {
        User user = userService.findByUuid(userId);
        List<MealResponseDto> results = oneMealRepositoryCustom.findMealsByDate(user, date);
        return results;
    }

    @GetMapping("/onemeal/{userId}")
    public List<OneMealResponseDto> findById(@PathVariable String userId) {
        return oneMealService.findByUser(userId);
    }


}
