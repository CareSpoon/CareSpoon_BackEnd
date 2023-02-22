package com.carespoon.dto;

import com.carespoon.domain.Image;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ImageDto {

    private Long imageId;
    private String originImageName;
    private String imageName;
    private String imagePath;

    public Image toEntity(){
        Image build = Image.builder()
                .imageId(imageId)
                .originImageName(originImageName)
                .imageName(imageName)
                .imagePath(imagePath)
                .build();
        return build;
    }
    @Builder

    public ImageDto(Long imageId, String originImageName, String imageName, String imagePath){
        this.imageId = imageId;
        this.originImageName = originImageName;
        this.imageName = imageName;
        this.imagePath = imagePath;
    }
}
