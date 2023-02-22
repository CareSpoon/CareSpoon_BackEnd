package com.carespoon.service;

import com.carespoon.Repository.ImageRepository;
import com.carespoon.domain.Image;
import com.carespoon.dto.ImageDto;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class ImageService {

    private ImageRepository imageRepository;

    public ImageService(ImageRepository imageRepository){
        this.imageRepository = imageRepository;
    }

    @Transactional
    public Long saveImage(ImageDto imageDto){
        return imageRepository.save(imageDto.toEntity()).getImageId();
    }

    @Transactional
    public ImageDto getImage(Long id){
        Image image = imageRepository.findById(id).get();

        ImageDto imageDto = ImageDto.builder()
                .imageId(id)
                .originImageName(image.getOriginImageName())
                .imageName(image.getImageName())
                .imagePath(image.getImagePath())
                .build();
        return imageDto;
    }
}
