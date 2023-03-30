package com.carespoon.Repository;

import com.carespoon.domain.User;
import com.querydsl.core.Tuple;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Date;
import java.util.List;

public interface OneMealRepositoryCustom {
    List<Tuple> findOneMealByCreatedTime(User user, Date date);
    List<Tuple> findOneMealByCreatedMonth(User user, YearMonth month);
}
