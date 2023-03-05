package com.carespoon.controller;

import com.carespoon.domain.Image;
import com.carespoon.dto.ImageDto;
import com.carespoon.dto.OneMealResponseDto;
import com.carespoon.dto.OneMealSaveRequestDto;
import com.carespoon.service.ImageService;
import com.carespoon.service.OneMealService;
import com.carespoon.util.MD5Generator;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

@RequiredArgsConstructor
@RestController
public class OneMealController {
    private final OneMealService oneMealService;


    @PostMapping("/onemeal")
    public Long save(@RequestBody OneMealSaveRequestDto requestDto) {

        return oneMealService.save(requestDto);
    }

    @GetMapping("/onemeal/{id}")
    public OneMealResponseDto findById(@PathVariable Long id) {
        return oneMealService.findById(id);
    }
    
}
