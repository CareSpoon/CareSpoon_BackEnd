package com.carespoon.domain;

import javax.persistence.*;

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
    private String imageName;

    @Column(nullable = false)
    private String imagePath;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "OneMeal_id")
    private OneMeal oneMeal;
    @Builder
    public Image(Long imageId,String imageName, String imagePath){
        this.imageId = imageId;
        this.imageName = imageName;
        this.imagePath = imagePath;
    }

}
