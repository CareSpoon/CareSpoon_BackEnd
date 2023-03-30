package com.carespoon.Repository;

import com.carespoon.domain.OneMeal;
import com.carespoon.domain.QOneMeal;
import com.carespoon.domain.User;
import com.querydsl.core.Tuple;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class OneMealRepositoryImpl extends QuerydslRepositorySupport implements OneMealRepositoryCustom {
    public OneMealRepositoryImpl(){
        super(OneMeal.class);
    }

    @Override
    public List<Tuple> findOneMealByCreatedTime(User user, Date date){
        QOneMeal oneMeal = QOneMeal.oneMeal;
        return from(oneMeal)
                .select(oneMeal.eatDate , oneMeal.meal_Kcal.sum() ,oneMeal.meal_Carbon.sum(), oneMeal.meal_Fat.sum(), oneMeal.meal_Protein.sum())
                .groupBy(oneMeal.eatDate)
                .where(oneMeal.eatDate.eq(date))
                .fetch();
    }

    @Override
    public List<Tuple> findOneMealByCreatedMonth(User user, YearMonth month){
        QOneMeal oneMeal = QOneMeal.oneMeal;
        return from(oneMeal)
                .select(oneMeal.eatDate , oneMeal.meal_Kcal.sum() ,oneMeal.meal_Carbon.sum(), oneMeal.meal_Fat.sum(), oneMeal.meal_Protein.sum())
                .groupBy(oneMeal.eatDate.month())
                .where(oneMeal.eatDate.month().eq(month.getMonthValue()))
                .fetch();
    }
}
