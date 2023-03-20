package com.carespoon.Repository;

import com.querydsl.core.Tuple;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

public interface OneMealRepositoryCustom {
    List<Tuple> findOneMealByCreatedTime(LocalDate date);
    List<Tuple> findOneMealByCreatedMonth(YearMonth month);
}
