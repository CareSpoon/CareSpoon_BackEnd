package com.carespoon.oneMeal.service;

import com.carespoon.exception.ErrorStatus;
import com.carespoon.exception.model.NotMealException;
import com.carespoon.exception.model.NotMenuException;
import com.carespoon.menu.service.MenuService;
import com.carespoon.oneMeal.dto.*;
import com.carespoon.oneMeal.repository.OneMealRepository;
import com.carespoon.menu.domain.Menu;
import com.carespoon.oneMeal.domain.OneMeal;
import com.carespoon.oneMeal.repository.OneMealRepositoryCustom;
import com.carespoon.user.domain.User;

import com.carespoon.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
@RequiredArgsConstructor
public class OneMealService {
    private final OneMealRepository oneMealRepository;
    private final UserRepository userRepository;
    private final GcsService gcsService;
    private final MenuService menuService;

    @Autowired
    @Qualifier("oneMealRepositoryImpl")
    private OneMealRepositoryCustom oneMealRepositoryCustom;


    public OneMeal saveTest(String userId, MultipartFile image, String tag) throws IOException, NotMenuException {
        WebClient webClient = WebClient.builder().baseUrl("http://endnjs.iptime.org:12300").build();

        //사진 형태 변환
        MultipartBodyBuilder builder = new MultipartBodyBuilder();
        builder.part("file", image.getResource());
        //webClient 사용해서 결과 받아오기
        Flux<String> result = webClient.post()
                .uri("/classification_only")
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .body(BodyInserters.fromMultipartData(builder.build())).retrieve().bodyToFlux(String.class);
        //result를 List로 변환하기
        List<String> resultList = result
                .flatMap(s -> Flux.fromArray(s.replaceAll("[\\[\\]\"]", "").split(",")))
                .map(String::trim).collectList().block();
//
//         각 메뉴의 영양 정보를 총합
        double totalKcal = 0.0;
        double totalCarbon = 0.0;
        double totalFat = 0.0;
        double totalProtein = 0.0;
        double totalNa = 0.0;
        double totalCal = 0.0;
        double totalFe = 0.0;
        List<Menu> menus = menuService.findByMenuName(resultList);
        for (Menu menu : menus) {
            totalKcal += menu.getMenu_kcal();
            totalCarbon += menu.getMenu_carbon();
            totalFat += menu.getMenu_fat();
            totalProtein += menu.getMenu_protein();
            totalNa += menu.getMenu_na();
            totalCal += menu.getMenu_cal();
            totalFe += menu.getMenu_fe();
        }
        User user = userRepository.findUserByUuid(userId);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dateOfString = new Date();
        String eatDate = dateFormat.format(dateOfString).toString();
        DateFormat formatMonth = new SimpleDateFormat("yyyy-MM");
        String eatMonth = formatMonth.format(dateOfString).toString();
        DateFormat formatTime = new SimpleDateFormat("HH:mm");
        String eatTime = formatTime.format(dateOfString).toString();
        // GCS에 이미지 업로드
        String imageUrl = gcsService.uploadImage(image);
        OneMealSaveRequestDto oneMealSaveRequestDto = new OneMealSaveRequestDto(totalKcal, totalCarbon, totalFat, totalProtein, totalNa, totalCal, totalFe, imageUrl, eatDate, eatMonth, eatTime, tag, user);

        // OneMeal 객체 저장
        return oneMealRepository.save(oneMealSaveRequestDto.toEntity());
    }

    public List<OneMealResponseDto> findByUser(String userId) {
        User user = userRepository.findUserByUuid(userId);
        List<OneMeal> entity = oneMealRepository.findByUser(user);
        List<OneMealResponseDto> result = new ArrayList<OneMealResponseDto>(entity.size());
        for (int i = 0; i < entity.size(); i++) {
            result.add(new OneMealResponseDto(entity.get(i)));
        }
        return result;
    }

    public List<MealResponseDto> findMealByCreatedDate(User user, String eatDate) throws NotMealException {
        List<OneMeal> oneMeals = oneMealRepository.findOneMealByEatDate(user, eatDate).orElseThrow(() -> new NotMealException(ErrorStatus.NOT_MEAL_FIND_EXCEPTION, ErrorStatus.NOT_MEAL_FIND_EXCEPTION.getMessage()));
        if (oneMeals.isEmpty()) {
            throw new NotMealException(ErrorStatus.NOT_MEAL_FIND_EXCEPTION, ErrorStatus.NOT_MEAL_FIND_EXCEPTION.getMessage());
        }
        List<MealResponseDto> responseDtos = new ArrayList<>();
        for (OneMeal meal : oneMeals) {
            responseDtos.add(new MealResponseDto(meal.getMeal_Kcal(), meal.getMeal_Carbon(), meal.getMeal_Fat(), meal.getMeal_Protein(), meal.getMeal_na(), meal.getMeal_cal(), meal.getMeal_fe(), meal.getImageUrl(), meal.getEatDate(), meal.getEatTime(), meal.getTag()));
        }
        return responseDtos;
    }

    public List<DailyMealResponseDto> findOneMealByCreatedDate(User user, String eatDate) throws NotMealException {
        List<OneMeal> oneMeals = oneMealRepository.findOneMealByEatDate(user, eatDate).orElseThrow(() -> new NotMealException(ErrorStatus.NOT_MEAL_FIND_EXCEPTION, ErrorStatus.NOT_MEAL_FIND_EXCEPTION.getMessage()));
        if (oneMeals.isEmpty()) {
            throw new NotMealException(ErrorStatus.NOT_MEAL_FIND_EXCEPTION, ErrorStatus.NOT_MEAL_FIND_EXCEPTION.getMessage());
        }
        return oneMealRepositoryCustom.findOneMealByCreatedTime(user, eatDate);
    }

    public List<MonthlyMealResponseDto> findOneMealByCreatedMonth(User user, String eatMonth) throws NotMealException {
        List<OneMeal> oneMeals = oneMealRepository.findOneMealByEatMonth(user, eatMonth).orElseThrow(() -> new NotMealException(ErrorStatus.NOT_MEAL_FIND_EXCEPTION, ErrorStatus.NOT_MEAL_FIND_EXCEPTION.getMessage()));
        if (oneMeals.isEmpty()) {
            throw new NotMealException(ErrorStatus.NOT_MEAL_FIND_EXCEPTION, ErrorStatus.NOT_MEAL_FIND_EXCEPTION.getMessage());
        }
        return oneMealRepositoryCustom.findOneMealByCreatedMonth(user, eatMonth);
    }
}
