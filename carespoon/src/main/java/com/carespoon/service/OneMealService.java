package com.carespoon.service;

import com.carespoon.Repository.OneMealRepository;
import com.carespoon.dto.OneMealSaveRequestDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OneMealService {
    private final OneMealRepository oneMealRepository;

    @Transactional
    public long save(OneMealSaveRequestDto requestDto){
        return oneMealRepository.save(requestDto.toEntity()).getId();
    }
}
