package com.carespoon.oneMeal.repository;

import com.carespoon.oneMeal.domain.OneMeal;
import com.carespoon.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface OneMealRepository extends JpaRepository<OneMeal, Long> , OneMealRepositoryCustom{
    List<OneMeal> findByUser(User user);
}
