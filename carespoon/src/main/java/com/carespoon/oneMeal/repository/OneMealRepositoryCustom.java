package com.carespoon.oneMeal.repository;

import com.carespoon.oneMeal.dto.DailyMealResponseDto;
import com.carespoon.oneMeal.dto.MealResponseDto;
import com.carespoon.oneMeal.dto.MonthlyMealResponseDto;
import com.carespoon.oneMeal.dto.OneMealResponseDto;
import com.carespoon.user.domain.User;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.time.YearMonth;
import java.util.Date;
import java.util.List;

public interface OneMealRepositoryCustom {
    List<DailyMealResponseDto> findOneMealByCreatedTime(User user, String eatDate);
    List<MonthlyMealResponseDto> findOneMealByCreatedMonth(User user, String eatMonth);

    List<MealResponseDto> findMealsByDate(User user, String eatDate);
}
