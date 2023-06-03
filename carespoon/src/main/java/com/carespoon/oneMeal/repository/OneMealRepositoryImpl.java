package com.carespoon.oneMeal.repository;

import com.carespoon.oneMeal.domain.OneMeal;
import com.carespoon.oneMeal.domain.QOneMeal;
import com.carespoon.oneMeal.dto.*;
import com.carespoon.user.domain.User;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;


public class OneMealRepositoryImpl extends QuerydslRepositorySupport implements OneMealRepositoryCustom {
    public OneMealRepositoryImpl(JPAQueryFactory queryFactory){
        super(OneMeal.class);
        this.queryFactory = queryFactory;
    }

    private final JPAQueryFactory queryFactory;

    @Override
    public List<DailyMealResponseDto> findOneMealByCreatedTime(User user, String eatDate){

        QOneMeal oneMeal = QOneMeal.oneMeal;
        QueryResults<DailyMealResponseDto> mealResponse =
                queryFactory.from(oneMeal)
                .select(new QDailyMealResponseDto(oneMeal.meal_Kcal.sum() ,oneMeal.meal_Carbon.sum(), oneMeal.meal_Fat.sum(), oneMeal.meal_Protein.sum(), oneMeal.meal_na.sum(), oneMeal.meal_cal.sum(), oneMeal.meal_fe.sum() ,oneMeal.eatDate))
                .groupBy(oneMeal.eatDate)
                .where(oneMeal.user.eq(user).and(oneMeal.eatDate.eq(eatDate)))
                .fetchResults();
        List<DailyMealResponseDto> result = mealResponse.getResults();
        return result;
    }

    @Override
    public List<MonthlyMealResponseDto> findOneMealByCreatedMonth(User user, String month){
        QOneMeal oneMeal = QOneMeal.oneMeal;
        QueryResults<MonthlyMealResponseDto> mealResponse=
            queryFactory.from(oneMeal)
                .select(new QMonthlyMealResponseDto(oneMeal.meal_Kcal.sum() ,oneMeal.meal_Carbon.sum(), oneMeal.meal_Fat.sum(), oneMeal.meal_Protein.sum(),  oneMeal.meal_na.sum(), oneMeal.meal_cal.sum(), oneMeal.meal_fe.sum() ,oneMeal.eatMonth ))
                .groupBy(oneMeal.eatMonth)
                .where(oneMeal.user.eq(user).and(oneMeal.eatMonth.eq(month)))
                .fetchResults();
        List<MonthlyMealResponseDto> result = mealResponse.getResults();
        return result;
    }

}
