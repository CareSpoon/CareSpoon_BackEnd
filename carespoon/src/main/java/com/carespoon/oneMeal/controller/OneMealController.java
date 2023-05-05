package com.carespoon.oneMeal.controller;

import com.carespoon.oneMeal.dto.OneMealRequestDto;
import com.carespoon.oneMeal.repository.OneMealRepositoryCustom;
import com.carespoon.oneMeal.service.GcsService;
import com.carespoon.user.domain.User;
import com.carespoon.oneMeal.dto.OneMealResponseDto;
import com.carespoon.oneMeal.service.OneMealService;
import com.carespoon.user.service.UserService;
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
import java.time.YearMonth;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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
        System.out.println(menuNames);
        OneMealResponseDto oneMeal = new OneMealResponseDto(oneMealService.save(userId, menuNames, image));
        return ResponseEntity.ok(oneMeal);
    }

    //사진 업로드 테스트 코드
//    private final GcsService gcsService;
//    @PostMapping("/imagetest")
//    public String addTest(@RequestBody MultipartFile file) throws IOException{
//        return gcsService.uploadImage(file);
//    }
    @GetMapping("/dailynurition/{userId}/{date}")
    public List<Tuple> getDaily(@PathVariable String userId, @PathVariable String date) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date listDate = dateFormat.parse(date);
        User user = userService.findByUuid(userId);
        List<Tuple> dailyNutrition = oneMealRepositoryCustom.findOneMealByCreatedTime(user, listDate);
        return dailyNutrition;
    }

    @GetMapping("/monthlynutrition/{userId}")
    public List<Tuple> getMonthly(@PathVariable String userId, @RequestParam String month) {
        YearMonth yearMonth = YearMonth.parse(month);
        User user = userService.findByUuid(userId);
        List<Tuple> monthlyNutrition = oneMealRepositoryCustom.findOneMealByCreatedMonth(user, yearMonth);
        return monthlyNutrition;
    }

    @GetMapping("/onemeal/{userId}")
    public List<OneMealResponseDto> findById(@PathVariable String userId) {
        return oneMealService.findByUser(userId);
    }


}
