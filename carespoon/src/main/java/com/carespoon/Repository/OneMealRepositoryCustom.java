package com.carespoon.Repository;

import com.carespoon.domain.OneMeal;
import com.querydsl.core.Tuple;

import java.util.List;

public interface OneMealRepositoryCustom {
    List<Tuple> findOneMealByCreatedTime();
}
