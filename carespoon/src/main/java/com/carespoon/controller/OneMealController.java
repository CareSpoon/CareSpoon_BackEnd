package com.carespoon.controller;

import com.carespoon.dto.OneMealResponseDto;
import com.carespoon.dto.OneMealSaveRequestDto;
import com.carespoon.service.OneMealService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class OneMealController {
    private final OneMealService oneMealService;

    @PostMapping("/onemeal")
    public Long save(@RequestBody OneMealSaveRequestDto requestDto){
        return oneMealService.save(requestDto);
    }

    @GetMapping("/onemeal/{id}")
    public OneMealResponseDto findById(@PathVariable Long id)
    {
        return oneMealService.findById(id);
    }
}
