package com.carespoon.oneMeal.service;

import com.carespoon.menu.service.MenuService;
import com.carespoon.oneMeal.repository.OneMealRepository;
import com.carespoon.menu.domain.Menu;
import com.carespoon.oneMeal.domain.OneMeal;
import com.carespoon.user.domain.User;
import com.carespoon.oneMeal.dto.OneMealResponseDto;
import com.carespoon.oneMeal.dto.OneMealSaveRequestDto;

import javax.transaction.Transactional;

import com.carespoon.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.ParseException;
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

//    @Transactional
//    public OneMeal save(String userId, List<String> menuNames, MultipartFile image) throws IOException, ParseException {
//        List<Menu> menus = menuService.findByMenuName(menuNames);
//
//        // 각 메뉴의 영양 정보를 총합
//        double totalKcal = 0.0;
//        double totalCarbon = 0.0;
//        double totalFat = 0.0;
//        double totalProtein = 0.0;
//        for (Menu menu : menus) {
//            totalKcal += menu.getMenu_Kcal();
//            totalCarbon += menu.getMenu_Carbon();
//            totalFat += menu.getMenu_Fat();
//            totalProtein += menu.getMenu_Protein();
//        }
//
//        User user = userRepository.findUserByUuid(userId);
//        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        Date dateOfString = new Date();
//        String eatDate = dateFormat.format(dateOfString).toString();
//        DateFormat formatMonth = new SimpleDateFormat("yyyy-MM");
//        String eatMonth = formatMonth.format(dateOfString).toString();
//
//        // GCS에 이미지 업로드
//        String imageUrl = gcsService.uploadImage(image);
//        OneMealSaveRequestDto oneMealSaveRequestDto = new OneMealSaveRequestDto(totalKcal, totalCarbon, totalFat, totalProtein, imageUrl, eatDate, eatMonth, user);
//
//        // OneMeal 객체 저장
//        return oneMealRepository.save(oneMealSaveRequestDto.toEntity());
//    }

    public OneMeal saveTest(String userId, MultipartFile image , String tag) throws IOException {
        WebClient webClient = WebClient.builder().baseUrl("http://43.128.112.3:8000").build();

//        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();

        //사진 형태 변환
        MultipartBodyBuilder builder = new MultipartBodyBuilder();
        builder.part("file", image.getResource());
//        Mono<List<String>> result = webClient
//                .post()
//                .uri("/predict")
//                .contentType(MediaType.MULTIPART_FORM_DATA)
//                .body(BodyInserters.fromMultipartData(builder.build()))
//                .retrieve()
//                .bodyToMono(List<String>.class);
        //webClient 사용해서 결과 받아오기
        Flux<String> result = webClient.post()
                .uri("/predict")
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .body(BodyInserters.fromMultipartData(builder.build())).retrieve().bodyToFlux(String.class);
        //result를 List로 변환하기
        List<String> resultList = result
                .flatMap(s -> Flux.fromArray(s.replaceAll("[\\[\\]\"]", "").split(",")))
                .map(String::trim).collectList().block();

        List<Menu> menus = menuService.findByMenuName(resultList);

        // 각 메뉴의 영양 정보를 총합
        double totalKcal = 0.0;
        double totalCarbon = 0.0;
        double totalFat = 0.0;
        double totalProtein = 0.0;
        for (Menu menu : menus) {
            totalKcal += menu.getMenu_Kcal();
            totalCarbon += menu.getMenu_Carbon();
            totalFat += menu.getMenu_Fat();
            totalProtein += menu.getMenu_Protein();
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
        OneMealSaveRequestDto oneMealSaveRequestDto = new OneMealSaveRequestDto(totalKcal, totalCarbon, totalFat, totalProtein, imageUrl, eatDate, eatMonth, eatTime, tag, user);

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

//    public List<OneMealResponseDto> findByUserandDate(String userId, String eatDate){
//        User user = userRepository.findUserByUuid(userId);
//        List<OneMeal> entity = oneMealRepository.findByUserandDate(user, eatDate);
//        List<OneMealResponseDto> result = new ArrayList<OneMealResponseDto>(entity.size());
//        for (int i = 0; i < entity.size(); i++) {
//            result.add(new OneMealResponseDto(entity.get(i)));
//        }
//        return result;
//    }

}
