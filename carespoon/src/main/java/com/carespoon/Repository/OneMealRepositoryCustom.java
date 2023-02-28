package com.carespoon.Repository;

import com.carespoon.domain.OneMeal;

import java.util.List;

public interface OneMealRepositoryCustom {
    List<OneMeal> findOneMealByCreatedTime();
}
