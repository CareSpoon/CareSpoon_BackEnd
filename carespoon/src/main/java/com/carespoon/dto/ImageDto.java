package com.carespoon.dto;

import com.carespoon.domain.Image;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ImageDto {

    private Long imageId;
    private String imageName;
    private String imagePath;

    public Image toEntity(){
        Image build = Image.builder()
                .imageId(imageId)
                .imageName(imageName)
                .imagePath(imagePath)
                .build();
        return build;
    }
    @Builder

    public ImageDto(Long imageId, String imageName, String imagePath){
        this.imageId = imageId;
        this.imageName = imageName;
        this.imagePath = imagePath;
    }
}
