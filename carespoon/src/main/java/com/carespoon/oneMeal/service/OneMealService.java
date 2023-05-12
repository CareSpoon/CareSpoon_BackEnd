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
import com.carespoon.user.service.UserService;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;
import java.util.List;


@Service
@RequiredArgsConstructor
public class OneMealService {
    private final OneMealRepository oneMealRepository;

    private final UserRepository userRepository;
    private final GcsService gcsService;
    private final MenuService menuService;

    @Transactional
    public OneMeal save(String userId, List<String> menuNames, MultipartFile image) throws IOException, ParseException {
        List<Menu> menus = menuService.findByMenuName(menuNames);

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

        // GCS에 이미지 업로드
        String imageUrl = gcsService.uploadImage(image);
        OneMealSaveRequestDto oneMealSaveRequestDto = new OneMealSaveRequestDto(totalKcal, totalCarbon, totalFat, totalProtein, imageUrl, eatDate,eatMonth, user);

        // OneMeal 객체 저장
        return oneMealRepository.save(oneMealSaveRequestDto.toEntity());
    }

    public OneMeal saveTest(String userId, MultipartFile image) throws IOException{
        WebClient webClient = WebClient
                .builder().baseUrl("").build();
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("photo", new InputStreamResource(image.getInputStream()));
        body.add("filename", image.getOriginalFilename());
        Mono<String> result = webClient
                .post()
                .uri("")
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .body(BodyInserters.fromMultipartData(body))
                .retrieve()
                .bodyToMono(String.class);
        List<String> resultList = result
                .flatMapMany(s -> Flux.fromArray(s.split(",")))
                .map(String::trim)
                .collectList()
                .block();

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

        // GCS에 이미지 업로드
        String imageUrl = gcsService.uploadImage(image);
        OneMealSaveRequestDto oneMealSaveRequestDto = new OneMealSaveRequestDto(totalKcal, totalCarbon, totalFat, totalProtein, imageUrl, eatDate,eatMonth, user);

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
