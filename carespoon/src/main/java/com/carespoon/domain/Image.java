package com.carespoon.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Image {

    @Id
    @GeneratedValue
    private Long imageId;

    @Column(nullable = false)
    private String originImageName;

    @Column(nullable = false)
    private String imageName;

    @Column(nullable = false)
    private String imagePath;

    @Builder
    public Image(Long imageId, String originImageName, String imageName, String imagePath){
        this.imageId = imageId;
        this.originImageName = originImageName;
        this.imageName = imageName;
        this.imagePath = imagePath;
    }

}
