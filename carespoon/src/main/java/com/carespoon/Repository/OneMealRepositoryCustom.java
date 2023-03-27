package com.carespoon.Repository;

import com.carespoon.domain.User;
import com.querydsl.core.Tuple;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.UUID;

public interface OneMealRepositoryCustom {
    List<Tuple> findOneMealByCreatedTime(User user, LocalDate date);
    List<Tuple> findOneMealByCreatedMonth(User user, YearMonth month);
}
