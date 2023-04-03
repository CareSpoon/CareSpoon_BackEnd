package com.carespoon.OneMeal.repository;

import com.carespoon.user.domain.User;
import com.querydsl.core.Tuple;

import java.time.YearMonth;
import java.util.Date;
import java.util.List;

public interface OneMealRepositoryCustom {
    List<Tuple> findOneMealByCreatedTime(User user, Date date);
    List<Tuple> findOneMealByCreatedMonth(User user, YearMonth month);
}
