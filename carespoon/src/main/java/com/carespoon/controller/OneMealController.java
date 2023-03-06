package com.carespoon.controller;

import com.carespoon.Repository.OneMealRepositoryCustom;
import com.carespoon.domain.Image;
import com.carespoon.dto.ImageDto;
import com.carespoon.dto.OneMealResponseDto;
import com.carespoon.dto.OneMealSaveRequestDto;
import com.carespoon.service.ImageService;
import com.carespoon.service.OneMealService;
import com.querydsl.core.Tuple;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class OneMealController {
    private final OneMealService oneMealService;
    private ImageService imageService;

    @Autowired
    @Qualifier("oneMealRepositoryImpl")
    private OneMealRepositoryCustom oneMealRepositoryCustom;

    @Getter
    @Setter
    public static class Daily{
        private LocalDate date;
        private Integer totalKcal;
        private Integer totalFat;
        private Integer totalProtein;
        private Integer totalCarbon;
        public Daily(LocalDate date, Integer totalKcal, Integer totalCarbon, Integer totalFat, Integer totalProtein){
            this.date = date;
            this.totalKcal = totalKcal;
            this.totalCarbon = totalCarbon;
            this.totalFat = totalFat;
            this.totalProtein = totalProtein;
        }
    }

    @GetMapping("/dailynurition")
    public List<Daily> getDaily(){
        List<Tuple> dailyNutrition = oneMealRepositoryCustom.findOneMealByCreatedTime();

        List<Daily> dailyNutritionList = new ArrayList<>();
        for(Tuple tuple: dailyNutrition) {
            LocalDate date = tuple.get(0,LocalDate.class);
            Integer totalKcal = tuple.get(1, Integer.class);
            Integer totalCarbon = tuple.get(2,Integer.class);
            Integer totalFat = tuple.get(3,Integer.class);
            Integer totalProtein = tuple.get(4, Integer.class);

            dailyNutritionList.add(new Daily(date, totalKcal,totalCarbon, totalFat, totalProtein));
        }
        return dailyNutritionList;
    }
    @PostMapping("/onemeal")
    public Long save(@RequestBody OneMealSaveRequestDto requestDto) {

        return oneMealService.save(requestDto);
    }

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
