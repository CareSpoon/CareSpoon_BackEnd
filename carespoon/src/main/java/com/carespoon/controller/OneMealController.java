package com.carespoon.controller;

import com.carespoon.Repository.OneMealRepositoryCustom;
import com.carespoon.domain.Image;
import com.carespoon.dto.ImageDto;
import com.carespoon.dto.OneMealResponseDto;
import com.carespoon.dto.OneMealSaveRequestDto;
import com.carespoon.service.ImageService;
import com.carespoon.service.OneMealService;
import com.querydsl.core.Tuple;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class OneMealController {
    private final OneMealService oneMealService;


    @Autowired
    @Qualifier("oneMealRepositoryImpl")
    private OneMealRepositoryCustom oneMealRepositoryCustom;

    @GetMapping("/dailynurition")
    public List<Tuple> getDaily(@RequestParam String date){
        LocalDate localDate = LocalDate.parse(date);
        List<Tuple> dailyNutrition = oneMealRepositoryCustom.findOneMealByCreatedTime(localDate);
        return dailyNutrition;
    }

    @GetMapping("/monthlynutrition")
    public List<Tuple> getMonthly(@RequestParam String month){
        YearMonth yearMonth = YearMonth.parse(month);
        List<Tuple> monthlyNutrition = oneMealRepositoryCustom.findOneMealByCreatedMonth(yearMonth);
        return  monthlyNutrition;
    }
    @PostMapping("/onemeal")
    public Long save(@RequestBody OneMealSaveRequestDto requestDto) {

        return oneMealService.save(requestDto);
    }

    private ImageService imageService;

    @PostMapping("/mealimage")
    public String handleFileUpload(@RequestParam("file") MultipartFile file){
        try{
            ImageDto imageDto = ImageDto.from(file);
            imageService.saveImage(imageDto);
        }catch (IOException e){
            e.printStackTrace();
        }
        return "redirect:/";
    }

    @GetMapping("/onemeal/{id}")
    public OneMealResponseDto findById(@PathVariable Long id) {
        return oneMealService.findById(id);
    }
    @GetMapping("/mealimage/{id}")
    public ResponseEntity<Resource> getImage(@PathVariable("id") Long imageId) throws IOException {
        Image image = imageService.getImage(imageId).toEntity();
        UrlResource resource = new UrlResource(image.getImagePath());

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + image.getImageName() + "\"")
                .body(resource);
    }

}
