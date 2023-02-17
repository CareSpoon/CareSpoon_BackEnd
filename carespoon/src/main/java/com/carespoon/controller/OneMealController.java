package com.carespoon.controller;

import com.carespoon.dto.OneMealSaveRequestDto;
import com.carespoon.service.OneMealService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class OneMealController {
    private final OneMealService oneMealService;

    @PostMapping("/onemeal")
    public Long save(@RequestBody OneMealSaveRequestDto requestDto){
        return oneMealService.save(requestDto);
    }
}
