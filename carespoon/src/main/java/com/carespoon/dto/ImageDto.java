package com.carespoon.dto;

import com.carespoon.domain.Image;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

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
    public ImageDto(String imageName, String imagePath){
        this.imageName = imageName;
        this.imagePath = imagePath;
    }

    public static ImageDto from(MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();
        String extension = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
        String newName = UUID.randomUUID() + "." + extension;
        String path = "/images/" + newName;
        byte[] bytes = file.getBytes();
        Path imagePath = Paths.get("src/main/resources/static/images/" + newName);
        Files.write(imagePath, bytes);

        return new ImageDto(originalFilename, path);
    }
}
