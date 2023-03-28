package com.carespoon.service;

import com.carespoon.Repository.OneMealRepository;
import com.carespoon.domain.OneMeal;
import com.carespoon.domain.User;
import com.carespoon.dto.OneMealResponseDto;
import com.carespoon.dto.OneMealSaveRequestDto;
import javax.transaction.Transactional;

import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;
import java.util.List;

@RequiredArgsConstructor
@Service
public class OneMealService {
    private final OneMealRepository oneMealRepository;

    private UserService userService;

    private Storage storage;
    @Value("${spring.cloud.gcp.storage.bucket}")
    private String bucketName;
    @Transactional
    public Long save(OneMealSaveRequestDto requestDto) throws IOException
    {
        String uuid = UUID.randomUUID().toString();
        String ext = requestDto.getImage().getContentType();

        BlobInfo blobInfo = storage.create(BlobInfo.newBuilder(bucketName, uuid).setContentType(ext).build(), requestDto.getImage().getInputStream());
        return oneMealRepository.save(requestDto.toEntity()).getId();
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
