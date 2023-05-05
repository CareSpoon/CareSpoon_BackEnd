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
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
        String date = new Date().toString();
        Date listDate = dateFormat.parse(date);

        // GCS에 이미지 업로드
        String imageUrl = gcsService.uploadImage(image);
        OneMealSaveRequestDto oneMealSaveRequestDto = new OneMealSaveRequestDto(totalKcal, totalCarbon, totalFat, totalProtein, imageUrl, listDate, user);

        // OneMeal 객체 저장
        return oneMealRepository.save(oneMealSaveRequestDto.toEntity());
    }


    public List<OneMealResponseDto> findByUser(String userId){
        User user = userRepository.findUserByUuid(userId);
        List<OneMeal> entity = oneMealRepository.findByUser(user);
        List<OneMealResponseDto> result = new ArrayList<OneMealResponseDto>(entity.size());
        for(int i = 0; i< entity.size(); i++){
            result.add(new OneMealResponseDto(entity.get(i)));
        }
        return result;
    }

}
