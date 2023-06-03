package com.carespoon.oneMeal.repository;

import com.carespoon.oneMeal.dto.DailyMealResponseDto;
import com.carespoon.oneMeal.dto.MealResponseDto;
import com.carespoon.oneMeal.dto.MonthlyMealResponseDto;
import com.carespoon.user.domain.User;
import java.util.List;

public interface OneMealRepositoryCustom {
    List<DailyMealResponseDto> findOneMealByCreatedTime(User user, String eatDate);
    List<MonthlyMealResponseDto> findOneMealByCreatedMonth(User user, String eatMonth);

}
