package com.carespoon.Repository;

import com.carespoon.domain.OneMeal;
import com.carespoon.domain.QOneMeal;
import com.querydsl.core.Tuple;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class OneMealRepositoryImpl extends QuerydslRepositorySupport implements OneMealRepositoryCustom {
    public OneMealRepositoryImpl(){
        super(OneMeal.class);
    }

    @Override
    public List<Tuple> findOneMealByCreatedTime(){
        QOneMeal oneMeal = QOneMeal.oneMeal;
        return from(oneMeal)
                .select(oneMeal.eatDate , oneMeal.meal_Kcal.sum() ,oneMeal.meal_Carbon.sum(), oneMeal.meal_Fat.sum(), oneMeal.meal_Protein.sum())
                .groupBy(oneMeal.eatDate)
                .fetch();
    }

    @Override
    public List<Tuple> findOneMealByCreatedMonth(){
        QOneMeal oneMeal = QOneMeal.oneMeal;
        return from(oneMeal)
                .select(oneMeal.eatDate , oneMeal.meal_Kcal.sum() ,oneMeal.meal_Carbon.sum(), oneMeal.meal_Fat.sum(), oneMeal.meal_Protein.sum())
                .groupBy(oneMeal.eatDate.month())
                .fetch();
    }
}
