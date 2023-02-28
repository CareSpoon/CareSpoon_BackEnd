package com.carespoon.domain;

import com.carespoon.Repository.OneMealRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.util.List;

public class OneMealRepositoryImpl implements OneMealRepositoryCustom {

    private JPAQueryFactory queryFactory;

    public OneMealRepositoryImpl(EntityManager em){
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<OneMeal> findOneMealByCreatedTime(){
        return queryFactory
                .selectFrom(QOneMeal.oneMeal)
                .orderBy(QOneMeal.oneMeal.createdDate.desc())
                .fetch();
    }
}
