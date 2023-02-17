package com.carespoon.service;

import com.carespoon.Repository.OneMealRepository;
import com.carespoon.domain.OneMeal;
import com.carespoon.dto.OneMealResponseDto;
import com.carespoon.dto.OneMealSaveRequestDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OneMealService {
    private final OneMealRepository oneMealRepository;

    @Transactional
    public long save(OneMealSaveRequestDto requestDto)
    {
        return oneMealRepository.save(requestDto.toEntity()).getId();
    }


    public OneMealResponseDto findById(Long id){
        OneMeal entity = oneMealRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException(("해당 식사는 존재하지 않습니다. id =")+ id));
        return new OneMealResponseDto(entity);
    }
}
