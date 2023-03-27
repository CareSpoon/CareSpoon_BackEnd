package com.carespoon.Repository;

import com.querydsl.core.Tuple;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.UUID;

public interface OneMealRepositoryCustom {
    List<Tuple> findOneMealByCreatedTime(UUID userId , LocalDate date);
    List<Tuple> findOneMealByCreatedMonth(UUID userId, YearMonth month);
}
