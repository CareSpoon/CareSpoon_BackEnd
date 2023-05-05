package com.carespoon.oneMeal.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.List;

@Getter
@NoArgsConstructor
public class OneMealRequestDto {
    private String uuid;
    private List<String> menuNames;
}
