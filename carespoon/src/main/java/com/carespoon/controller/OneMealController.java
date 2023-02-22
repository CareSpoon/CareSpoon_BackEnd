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
    private OneMealService oneMealService;
    private ImageService imageService;

    public OneMealController(OneMealService oneMealService, ImageService imageService){
        this.oneMealService = oneMealService;
        this.imageService = imageService;
    }
    @PostMapping("/onemeal")
    public Long save(@RequestBody OneMealSaveRequestDto requestDto) {
        return oneMealService.save(requestDto);
    }

    @PostMapping("/imageupload")
    public String imageupload(@RequestParam("image")MultipartFile images, OneMealSaveRequestDto requestDto){
        try {
            String originImageName = images.getOriginalFilename();
            assert originImageName != null;
            String imageName = new MD5Generator(originImageName).toString();

            String savePath = System.getProperty("user.dir") + "\\images";
            if(!new File(savePath).exists()){
                try {
                    new File(savePath).mkdir();
                }catch (Exception e){
                    e.getStackTrace();
                }
            }
            String imagePath = savePath + "\\"+ imageName;
            images.transferTo(new File(imagePath));

            ImageDto imageDto = new ImageDto();
            imageDto.setOriginImageName(originImageName);
            imageDto.setImageName(imageName);
            imageDto.setImagePath(imagePath);

            Long imageId = imageService.saveImage(imageDto);
            requestDto.setImageId(imageId);
            oneMealService.save(requestDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/";
    }
    @GetMapping("/onemeal/{id}")
    public OneMealResponseDto findById(@PathVariable Long id) {
        return oneMealService.findById(id);
    }

}
