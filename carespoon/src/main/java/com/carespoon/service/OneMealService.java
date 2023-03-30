package com.carespoon.service;

import com.carespoon.Repository.MenuRepository;
import com.carespoon.Repository.OneMealRepository;
import com.carespoon.domain.Menu;
import com.carespoon.domain.OneMeal;
import com.carespoon.domain.User;
import com.carespoon.dto.OneMealResponseDto;
import com.carespoon.dto.OneMealSaveRequestDto;
import javax.transaction.Transactional;

import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;
import java.util.List;


@Service
public class OneMealService {
    private OneMealRepository oneMealRepository;

    private UserService userService;

    private GcsService gcsService;
    private MenuService menuService;
    private Storage storage;


    public OneMealService(){
        this.storage = StorageOptions.getDefaultInstance().getService();
    }
    @Transactional
    public OneMeal save(UUID userId, List<String> menuNames, MultipartFile image) throws IOException
    {
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

        User user = userService.findByUuid(userId);

        // GCS에 이미지 업로드
        String imageUrl = gcsService.uploadImage(image);
        OneMealSaveRequestDto oneMealSaveRequestDto = new OneMealSaveRequestDto(totalKcal, totalCarbon, totalFat, totalProtein, imageUrl, new Date(), user);

        // OneMeal 객체 저장
        return oneMealRepository.save(oneMealSaveRequestDto.toEntity());
    }


    public List<OneMealResponseDto> findByUser(UUID userId){
        User user = userService.findByUuid(userId);
        List<OneMeal> entity = oneMealRepository.findByUser(user);
        List<OneMealResponseDto> result = new ArrayList<OneMealResponseDto>(entity.size());
        for(int i = 0; i< entity.size(); i++){
            result.add(new OneMealResponseDto(entity.get(i)));
        }
        return result;
    }

}
